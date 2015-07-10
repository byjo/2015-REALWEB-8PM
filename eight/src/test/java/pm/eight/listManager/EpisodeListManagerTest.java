package pm.eight.listManager;


import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pm.eight.listManager.EpisodeListManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-applicationContext.xml")
public class EpisodeListManagerTest {

	private static final Logger logger = LoggerFactory.getLogger(EpisodeListManagerTest.class);
	@Autowired
	private EpisodeListManager manager;
	
	@Test
	public void getDayTest() throws Exception {
		Class<?> clazz = manager.getClass();
		Object obj = clazz.newInstance();
		
		Method getDay = clazz.getDeclaredMethod("getDay");
		getDay.setAccessible(true);
		
		logger.debug("today : {}", getDay.invoke(obj, null));
	}
	
	@Test
	public void getListTest() {
		assertEquals(manager.getEpisodeList().size(), 2);
	}

}
