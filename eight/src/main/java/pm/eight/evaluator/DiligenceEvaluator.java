package pm.eight.evaluator;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

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
		long delayTime;
		Comic comic = episode.getComic();

		String comicPageLink = comic.getLink();
		ComicPageDTO comicPageDTO = crawler.crawlComicPage(comicPageLink);

		String episodePageLink = comicPageDTO.getLatestLink();
		EpisodePageDTO episodePageDTO = crawler.crawlEpisodePage(episodePageLink);

		if ((delayTime = getDelayTime(comic)) > 0) {
			episode.setDelayTime(delayTime);
			episode.setWebtookStateCode(WebtoonStateType.DELAY);
		} else {
			episode.setWebtookStateCode(WebtoonStateType.PUBLISH);
		}

		episode.setLink(episodePageLink);
		episode.setAmount(episodePageDTO.getAmount());

		episodeRepository.save(episode);
	}

	private long getDelayTime(Comic comic) {
		WeekFrequencyType weekFrequencyType = comic.getWeekFrequencyCode();

		LocalDate previousPublishDay = getPreviousPublishDay(comic.getId());
		LocalDate expectedPublishDay = dateManager.getExpectedPublishDay(previousPublishDay, weekFrequencyType);
		Duration duration = Duration.between(expectedPublishDay.atTime(0, 0), LocalDateTime.now());
		return duration.toHours();
	}

	private LocalDate getPreviousPublishDay(long comicId) {
		Episode previousEpisode = episodeRepository.getLatestEpisodeInDB(comicId);
		LocalDate previousPublishDay = LocalDateTime
				.ofInstant(previousEpisode.getCreateDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
		return previousPublishDay;
	}
}
