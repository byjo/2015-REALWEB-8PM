package pm.eight.listManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;
import pm.eight.repository.ComicRepository;
import pm.eight.util.DateManager;

@Component
public class EpisodeListManager {
	private static final Logger logger = LoggerFactory
			.getLogger(EpisodeListManager.class);
	@Resource(name="episodeList")
	private List<Episode> episodeList;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private DateManager dateManager;
	
	@Scheduled(cron="0 34 17 * * ?")
	public void updateEpisodeList() {
		System.out.println("Q!!!!");
		dateManager.initDate();
//		dateManager.setTomorrow();
		
		Date date = dateManager.getMidnightDate();
		String day = dateManager.getDayOfWeek();
		List<Comic> comicList = comicRepository.findByDate(day);
		
		for (Comic comic : comicList) {
			Episode episode = new Episode(date, comic);
			
			episodeList.add(episode);
			//episodeList.add(new Episode(date, comic));
			logger.debug("episode: {}", episode.toString());
			logger.debug("나 episodeList: {}", episodeList);
		}
	}

	public List<Episode> getEpisodeList() {
		return episodeList;
	}

	public void setRepository(ComicRepository comicRepository) {
		this.comicRepository = comicRepository;
	}

	public DateManager getDateManager() {
		return dateManager;
	}

	public void setDateManager(DateManager dateManager) {
		this.dateManager = dateManager;
	}
	
}
