package pm.eight.listManager;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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
import pm.eight.listManager.EpisodeListManager;
import pm.eight.repository.ComicRepository;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:/test-applicationContext.xml")
@ContextConfiguration("classpath:/crawler-servlet.xml")
public class EpisodeListManagerTest {
	private static final Logger logger = LoggerFactory.getLogger(EpisodeListManagerTest.class);
	@Autowired
	private EpisodeListManager manager;
	
	private ComicRepository mockRepository = mock(ComicRepository.class);
	
	@Before
	public void setup() {
		manager.setRepository(mockRepository);
		
		List<Comic> comics = new ArrayList<Comic>();
		comics.add(new Comic("link", "thumbnail_uri", "title" ,"EVERY"));
		when(mockRepository.findByDate("Mon")).thenReturn(comics);
	}
	
	@Test
	public void getListTest() {
		assertEquals(manager.getEpisodeList().size(), 2);
	}
	
	@Test
	public void updateListTest() {
		manager.updateEpisodeList();
		logger.debug("episode list: {}", manager.getEpisodeList().toString());
	}

}
