package pm.eight.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pm.eight.domain.Comic;

@Transactional
@Repository
public class ComicRepository extends AbstractRepository<Comic>{
	
}