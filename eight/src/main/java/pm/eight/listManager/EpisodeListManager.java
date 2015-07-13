package pm.eight.listManager;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void updateEpisodeList() {
		dateManager.initDate();
		String day = dateManager.getDayOfWeek();
		List<Comic> comicList = comicRepository.findByDate(day);
		for (Comic comic : comicList) {
			episodeList.add(new Episode(comic));
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
	
}
