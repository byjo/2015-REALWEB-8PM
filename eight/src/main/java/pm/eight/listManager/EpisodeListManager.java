package pm.eight.listManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;
import pm.eight.repository.ComicRepository;

@Component
public class EpisodeListManager {
	
	@Resource(name="episodeList")
	private List<Episode> episodeList;
	
	@Autowired
	private ComicRepository comicRepository;
	
	public void listUpdate() {
		String day = getDay();
		List<Comic> comicList = comicRepository.findByDate(day);
		for (Comic comic : comicList) {
			episodeList.add(new Episode(comic));
		}
	}

	private String getDay() {
		Date date = new Date();
		SimpleDateFormat dateForm = new SimpleDateFormat("E", Locale.ENGLISH);
		String day = dateForm.format(date);

		return day;
	}

	public List<Episode> getEpisodeList() {
		return episodeList;
	}
	
}
