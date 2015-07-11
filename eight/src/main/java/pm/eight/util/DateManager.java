package pm.eight.util;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateManager {
	public String getDayOfWeek() {
		DateTime dateTime = new DateTime();
		DateTime.Property dayOfWeekProperty = dateTime.dayOfWeek();
		return dayOfWeekProperty.getAsShortText(Locale.ENGLISH);
	}

	public String getYesterday() {
		LocalDate date = LocalDate.now();
		date = date.minusDays(1);
		DateTimeFormatter dateForm = DateTimeFormat.forPattern("yyyy.MM.dd"); 
		String yesterday = date.toString(dateForm);
		return yesterday;
	}
}
