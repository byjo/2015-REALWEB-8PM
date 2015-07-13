package pm.eight.evaluator;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pm.eight.domain.Episode;
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
		EpisodePageDTO episodePageDTO = crawler.crawlEpisodePage("episodeLink");

		if (isOnTime(episodePageDTO)) {
			episode.setWebtookStateCode(WebtoonStateType.PUBLISH);
		} else {
//			episode.setDelayTime(delayTime);
//			TODO: delayTime 계산법 생각해야함			
			episode.setWebtookStateCode(WebtoonStateType.DELAY);
		}

		episode.setLink(episodePageDTO.getLink());
		episode.setAmount(episodePageDTO.getAmount());

		episodeRepository.save(episode);
	}

	private boolean isOnTime(EpisodePageDTO episodePageDTO) {
		return episodePageDTO.getPublishingDay() == dateManager.getYesterday();
	}
	
}


