package pm.eight.evaluator;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;
import pm.eight.dto.ComicPageDTO;
import pm.eight.dto.EpisodePageDTO;
import pm.eight.enums.WebtoonStateType;
import pm.eight.repository.EpisodeRepository;
import pm.eight.util.DateManager;
import pm.eight.util.WebtoonCrawler;

@Component
public class DiligenceEvaluator {

	@Autowired
	EpisodeRepository episodeRepository;

	@Autowired
	DateManager dateManager;

	@Autowired
	WebtoonCrawler crawler;

	public void evaluate(Episode episode) throws IOException {
		Comic comic = episode.getComic();
		
		String comicPageLink = "http://comic.naver.com" + comic.getLink();
		ComicPageDTO comicPageDTO = crawler.crawlComicPage(comicPageLink);
		System.out.println(comicPageLink);
		String episodePageLink = comicPageDTO.getLatestLink();
		EpisodePageDTO episodePageDTO = crawler.crawlEpisodePage(episodePageLink);
		System.out.println(episodePageLink);
		//Test시 필요
//		episode.setCreateDate(new Date());

		long delayTime;
		if ((delayTime = dateManager.calculateDelayTime(episode.getCreateDate())) > 0) {
			episode.setDelayTime(delayTime);
			episode.setWebtookStateCode(WebtoonStateType.DELAY);
		} else {
			episode.setWebtookStateCode(WebtoonStateType.PUBLISH);
		}

		System.out.println("?????");
		episode.setTitle("제1화 ㅋ");
		System.out.println(episodePageLink);
		episode.setLink(episodePageLink);
		episode.setAmount(episodePageDTO.getAmount());
		System.out.println(episode.getLink());
		System.out.println(episode.toString());
		
		episodeRepository.save(episode);
	}
}
