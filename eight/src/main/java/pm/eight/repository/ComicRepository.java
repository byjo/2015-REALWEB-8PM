package pm.eight.repository;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pm.eight.domain.Comic;
import pm.eight.domain.Episode;

@Transactional
@Repository
public class ComicRepository extends AbstractRepository<Comic>{

	public List<Comic> findByDate(String day) {
		Query query = getSession().createQuery("from Comic as comic where comic.week like :day");
		query.setParameter("day", "%" + day + "%");
		return query.list();
	}
	
}