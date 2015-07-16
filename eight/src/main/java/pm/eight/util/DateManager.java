package pm.eight.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

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
	
	public long calculateDelayTime(Date createDate) {
		Instant createTime = Instant.ofEpochMilli(createDate.getTime());
		Instant criteriaTime = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();		
		Duration duration = Duration.between(criteriaTime, createTime);
		return duration.toHours();
	}

}
