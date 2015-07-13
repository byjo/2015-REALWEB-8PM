package pm.eight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pm.eight.domain.Episode;

@Repository
public class EpisodeRepository extends AbstractRepository{

	public Episode findLatestEpisode(long cominId) {
		Episode result = (Episode) getSession().createQuery("from Episode ORDER BY 'create_date' DESC")
                .setMaxResults(1)
                .uniqueResult();
		return result;
		}

}