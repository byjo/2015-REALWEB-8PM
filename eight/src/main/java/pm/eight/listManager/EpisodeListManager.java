package pm.eight.listManager;

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
	
	//TODO 11시 30분에 만들면서, 각 episode list의 create_time을 다음날 정각으로 맞춤
	@Scheduled(cron="0 30 22 * * ?")
	public void updateEpisodeList() {
		dateManager.initDate();
		
		String day = dateManager.getDayOfWeek();
		List<Comic> comicList = comicRepository.findByDate(day);
		
		for (Comic comic : comicList) {
			episodeList.add(dateManager.getDate(), new Episode(comic));
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
