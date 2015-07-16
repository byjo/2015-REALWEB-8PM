package pm.eight.repository;
import org.hibernate.Query;
//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pm.eight.domain.Episode;

@Repository
public class EpisodeRepository extends AbstractRepository{

	public Episode findLatestEpisode() {
		Query query = getSession().createQuery("from Episode where id = 1");
		System.out.println(query.list());
		
		return (Episode) query.list().get(0);
		}
}