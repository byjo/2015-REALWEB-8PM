package pm.eight.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pm.eight.domain.Image;

@Transactional
@Repository
public class ImageRepository extends AbstractRepository<Image>{

}