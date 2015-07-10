package pm.eight.repository;


import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pm.eight.domain.Comic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/crawler-servlet.xml")
public class ComicRepositoryTest {
	private static final Logger logger = LoggerFactory.getLogger(ComicRepositoryTest.class);
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Test
	public void findByDateTest() {
		String day = "Fri";
		List<Comic> comicList = comicRepository.findByDate(day);
		assertEquals(comicList.size(), 3);
		assertEquals(comicList.get(2).getId(), Long.valueOf(4));
	}

}
