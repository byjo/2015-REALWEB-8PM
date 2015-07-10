package pm.eight.evaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.enums.WeekFrequencyType;
import pm.eight.repository.ComicRepository;

@Component
public class DiligenceEvaluator {
	
	@Autowired
	ComicRepository comicRepository;
	
	public void setComicRepository(ComicRepository comicRepository) {
		this.comicRepository = comicRepository;
	}

	public Comic evaluateDiligence(){
		comicRepository.save(new Comic("link","thum_url", "title", WeekFrequencyType.EVEN));
		Comic comic = comicRepository.find((long)1);
		return comic;
	}
}
