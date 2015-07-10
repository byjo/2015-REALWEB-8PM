package pm.eight.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pm.eight.domain.Author;

@Transactional
@Repository
public class AuthorRepository extends AbstractRepository<Author> {

}
