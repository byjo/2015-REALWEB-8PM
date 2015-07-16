package pm.eight.listManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;
import pm.eight.repository.ComicRepository;
import pm.eight.util.DateManager;

@Component
public class EpisodeListManager {
	
	@Resource(name="episodeList")
	private List<Episode> episodeList;
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private DateManager dateManager;
	
//	@Scheduled(cron="0 30 22 * * ?")
//	public List<Episode> updateEpisodeList() {
	public void updateEpisodeList() {
//		episodeList = new ArrayList<Episode>();
		dateManager.setTomorrow();
		
		Date date = dateManager.getMidnightDate();
		String day = dateManager.getDayOfWeek();
		List<Comic> comicList = comicRepository.findByDate(day);
		
		for (Comic comic : comicList) {
			episodeList.add(new Episode(date, comic));
		}
//		return episodeList;
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
