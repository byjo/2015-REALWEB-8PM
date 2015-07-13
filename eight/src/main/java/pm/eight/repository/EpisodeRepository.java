package pm.eight.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pm.eight.domain.Episode;

@Transactional
@Repository
public class EpisodeRepository extends AbstractRepository<Episode> {

	public Episode getLatestEpisodeInDB(long comicId) {
		
		return null;
	}

}