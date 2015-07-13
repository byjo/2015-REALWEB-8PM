package pm.eight.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;

import java.util.Locale;

import org.springframework.stereotype.Component;

import pm.eight.enums.WeekFrequencyType;

@Component
public class DateManager {
	
	LocalDate date;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}
	
	public void initDate(){
		date = LocalDate.now();
	}

	public String getDayOfWeek() {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	}

	public String getYesterday() {
		date = date.minusDays(1);
		String yesterday = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
		return yesterday;
	}

	public LocalDate getExpectedPublishDay(LocalDate previousPublishDay, WeekFrequencyType weekFrequencyType) {
		// TODO Auto-generated method stub
		return null;
	}
}
