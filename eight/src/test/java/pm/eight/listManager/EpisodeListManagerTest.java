package pm.eight.listManager;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-applicationContext.xml")
//@ContextConfiguration("classpath:/crawler-servlet.xml")
public class EpisodeListManagerTest {
	private static final Logger logger = LoggerFactory.getLogger(EpisodeListManagerTest.class);

	@InjectMocks
	private EpisodeListManager manager;
	
	@Mock
	private ComicRepository mockRepository;
	
	@Mock
	private DateManager mockDateManager;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getListTest() {
		assertEquals(manager.getEpisodeList().size(), 0);
	}
	
	@Test
	public void updateListTest() {
		Mockito.when(mockDateManager.getDayOfWeek()).thenReturn("Tue");
		
		List<Comic> comics = new ArrayList<Comic>();
		comics.add(new Comic("link", "thumbnail_uri", "example title" ,"EVERY"));
		Mockito.when(mockRepository.findByDate("Tue")).thenReturn(comics);
		
		manager.updateEpisodeList();
		logger.debug("episode list: {}", manager.getEpisodeList().toString());
	}

}
