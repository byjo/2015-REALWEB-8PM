package pm.eight.evaluator;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;
import pm.eight.dto.ComicPageDTO;
import pm.eight.dto.EpisodePageDTO;
import pm.eight.enums.WebtoonStateType;
import pm.eight.enums.WeekFrequencyType;
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

		String comicPageLink = comic.getLink();
		ComicPageDTO comicPageDTO = crawler.crawlComicPage(comicPageLink);

		String episodePageLink = comicPageDTO.getLatestLink();
		EpisodePageDTO episodePageDTO = crawler.crawlEpisodePage(episodePageLink);

		long delayTime;
		if ((delayTime = dateManager.calculateDelayTime(comic.getCreateDate())) > 0) {
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
