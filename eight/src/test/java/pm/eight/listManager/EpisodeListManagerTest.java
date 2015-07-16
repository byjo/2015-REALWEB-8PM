package pm.eight.listManager;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pm.eight.domain.Comic;
import pm.eight.listManager.EpisodeListManager;
import pm.eight.repository.ComicRepository;
import pm.eight.util.DateManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-applicationContext.xml")
public class EpisodeListManagerTest {
	private static final Logger logger = LoggerFactory.getLogger(EpisodeListManagerTest.class);
	
	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private DateManager dateManager;
	
	@Autowired
	private EpisodeListManager manager;
	
	
	@Test
	public void getListTest() {
		assertEquals(manager.getEpisodeList().size(), 3);
	}
	
	@Test
	public void updateListTest() {
		Mockito.when(dateManager.getDayOfWeek()).thenReturn("Tue");
		Mockito.when(dateManager.getMidnightDate()).thenReturn(new Date());
		
		List<Comic> comics = new ArrayList<Comic>();
		comics.add(new Comic("link", "Tue", "thumbnail_uri", "example title"));
		Mockito.when(comicRepository.findByDate("Tue")).thenReturn(comics);
		
		manager.updateEpisodeList();
		assertEquals(comics.get(0), manager.getEpisodeList().get(3).getComic());
	}
	
}
