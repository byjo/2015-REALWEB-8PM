package pm.eight.checker;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import pm.eight.domain.Episode;
import pm.eight.enums.WebtoonStateType;
import pm.eight.evaluator.DiligenceEvaluator;
import pm.eight.repository.EpisodeRepository;

@Component
public class PublishChecker extends QuartzJobBean {

	@Resource(name = "episodeList")
	private List<Episode> episodeList; 

	@Autowired
	DiligenceEvaluator diligenceEvaluator;
	
	@Autowired
	EpisodeRepository episodeRepository;

	private final String PUBLISH_CHECKER_URI = "http://comic.naver.com/webtoon/weekday.nhn";
	private final String LIST_SELECTOR = ".col_selected ul li > div.thumb > a";

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Map<String, WebtoonStateType> epStateList = getEpisodeStateList();
		for (Episode episode : episodeList) {
			// 연재 상태이면 evaluator에게, 휴재상태이면
			WebtoonStateType state = epStateList.get(episode.getComic().getTitle());
			if (state == WebtoonStateType.PUBLISH) {
				try {
					diligenceEvaluator.evaluate(episode);
				} catch (IOException e) {
					e.printStackTrace();
				}
				episodeList.remove(episode);
				continue;
			}

			if (state == WebtoonStateType.SUSPEND) {
				episode.setWebtookStateCode(WebtoonStateType.SUSPEND);
				episodeRepository.save(episode);
				episodeList.remove(episode);
			}
		}
	}

	Map<String, WebtoonStateType> getEpisodeStateList() {
		// indent가 깊다. 리팩토링 필요
		Document doc = null;
		try {
			doc = Jsoup.connect(PUBLISH_CHECKER_URI).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements episodeInfoList = doc.select(LIST_SELECTOR); // 이 결과를 주입받을 수 있어서 테스트하기 쉬우면 좋겠다...
		HashMap<String, WebtoonStateType> episodeStateList = new HashMap<String, WebtoonStateType>();
		for (Element el : episodeInfoList) {
			Element elImage = el.select("img[src]").first();
			String comicTitle = elImage.attr("title");

			Element elEm = el.select("em").first();
			if (elEm != null) {
				if (elEm.hasClass("ico_updt")) {
					episodeStateList.put(comicTitle, WebtoonStateType.PUBLISH);
				} else if (elEm.hasClass("ico_break")) {
					episodeStateList.put(comicTitle, WebtoonStateType.SUSPEND);
				}

			}
		}
		return episodeStateList;
	}
}
