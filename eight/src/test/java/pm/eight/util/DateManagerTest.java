package pm.eight.util;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateManagerTest {
	
	private DateManager dateManager;
	
	@Before
	public void setup(){
		dateManager= new DateManager();
		dateManager.setDate(LocalDate.parse("2015-07-13"));
	}
	
	@Test
	public void getDayOfWeekTest() throws Exception {
		String dayOfWeek = dateManager.getDayOfWeek();
		assertEquals("Mon", dayOfWeek);
	}
	
	@Test
	public void getDayOfWeekFailTest() throws Exception {
		String dayOfWeek = dateManager.getDayOfWeek();
		assertNotEquals("Tue", dayOfWeek);
	}
	
	@Test
	public void getYesterdayTest() throws Exception {
		 String yesterDay= dateManager.getYesterday();
		 assertEquals("2015.07.12", yesterDay);
	}
	
	@Test
	public void getYesterdayFailTest() throws Exception {
		 String yesterDay= dateManager.getYesterday();
		 assertNotEquals("2015.07.17", yesterDay);
	}
	
	@Test
	public void calculateDelayTimeTest() throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2015-07-15");
		long delayHour = dateManager.calculateDelayTime(date);
		assertEquals(24, delayHour);
	}
	
	@Test
	public void convertDateTest() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2015-07-13");
		Date convertedDate = dateManager.getMidnightDate();
		assertEquals(date, convertedDate);
	}
}
