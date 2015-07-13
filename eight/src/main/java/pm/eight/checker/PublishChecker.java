package pm.eight.checker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;
import pm.eight.enums.WebtoonStateType;
import pm.eight.evaluator.DiligenceEvaluator;

@Component
public class PublishChecker {
	//@Autowired
	private List<Episode> episodeList; //autowired가 잘 안됨...크론으로 스케쥴링 돌 때, episodeList가 size가 0보다 클 때만 run하게 할 수 있나?
	
	@Autowired
	DiligenceEvaluator diligenceEvaluator;
	
	private final String PUBLISH_CHECKER_URI = "http://comic.naver.com/webtoon/weekday.nhn"; 
	private final String LIST_SELECTOR = ".col_selected ul li > div.thumb > a";
	
	public void publishCheck () throws IOException {
		Map<String, WebtoonStateType> epStateList = getEpisodeStateList();
		for(Episode episode : episodeList) {
			//연재 상태이면 evaluator에게, 휴재상태이면 
			WebtoonStateType state = epStateList.get(episode.getTitle());
			if(state == WebtoonStateType.PUBLISH) {
				diligenceEvaluator.evaluateDiligence(episode);
				episodeList.remove(episode);
				continue;
			}
			
			if(state == WebtoonStateType.SUSPEND) {
				episode.setWebtookStateCode(WebtoonStateType.SUSPEND);
				episodeList.remove(episode);
			}			
		}
	}
	
	Map<String, WebtoonStateType> getEpisodeStateList () throws IOException {
		//indent가 깊다. 리팩토링 필요
		Document doc = null;
		doc = Jsoup.connect(PUBLISH_CHECKER_URI).get();
		Elements episodeInfoList = doc.select(LIST_SELECTOR); //이 결과를 주입받을 수 있어서 테스트하기 쉬우면 좋겠다...
		HashMap<String, WebtoonStateType> episodeStateList = new HashMap<String, WebtoonStateType>();
		for (Element el : episodeInfoList) {
			Element elImage = el.select("img[src]").first();
			String comicTitle = elImage.attr("title");

			Element elEm = el.select("em").first();
			if(elEm != null) {
				if(elEm.hasClass("ico_updt")) {
					episodeStateList.put(comicTitle, WebtoonStateType.PUBLISH);
				}
				else if(elEm.hasClass("ico_break")) {
					episodeStateList.put(comicTitle, WebtoonStateType.SUSPEND);
				}
				
			}
			else {
				episodeStateList.put(comicTitle, WebtoonStateType.DELAY);
			}
		}
		return episodeStateList;
	}
}
