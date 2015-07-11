package pm.eight.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateManagerTest {
	
	private DateManager dateManager;
	
	@Before
	public void setup(){
		dateManager= new DateManager();
	}
	
	@Test
	public void getDayOfWeekTest() throws Exception {
		String dayOfWeek = dateManager.getDayOfWeek();
		assertEquals("Sat", dayOfWeek);
	}
	
	@Test
	public void getDayOfWeekFailTest() throws Exception {
		String dayOfWeek = dateManager.getDayOfWeek();
		assertNotEquals("Tue", dayOfWeek);
	}
	
	@Test
	public void getFormmattedDateTest() throws Exception {
		 String formattedDate= dateManager.getYesterday();
		 assertEquals("2015.07.10", formattedDate);
	}
	
	@Test
	public void getFormmattedDateFailTest() throws Exception {
		 String formattedDate= dateManager.getYesterday();
		 assertNotEquals("2015.07.17", formattedDate);
	}
}