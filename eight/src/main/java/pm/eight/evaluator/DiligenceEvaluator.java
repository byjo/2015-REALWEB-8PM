package pm.eight.evaluator;

import java.io.IOException;

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
		
		String episodePageLink = comicPageDTO.getLatestLink();
		EpisodePageDTO episodePageDTO = crawler.crawlEpisodePage(episodePageLink);

		long delayTime;
		if ((delayTime = dateManager.calculateDelayTime(episode.getCreateDate())) > 0) {
			episode.setDelayTime(delayTime);
			episode.setWebtookStateCode(WebtoonStateType.DELAY);
		} else {
			episode.setWebtookStateCode(WebtoonStateType.PUBLISH);
		}

		episode.setLink(episodePageLink);
		episode.setAmount(episodePageDTO.getAmount());
		
		episodeRepository.save(episode);
	}
}
