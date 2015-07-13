package pm.eight.util;

import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.LocalDate.Property;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class DateManager {
	
	LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}

	public String getDayOfWeek() {
		Property dayOfWeekProperty = date.dayOfWeek();
		return dayOfWeekProperty.getAsShortText(Locale.ENGLISH);
	}

	public String getYesterday() {
		date = date.minusDays(1);
		DateTimeFormatter dateForm = DateTimeFormat.forPattern("yyyy.MM.dd"); 
		String yesterday = date.toString(dateForm);
		return yesterday;
	}
}
