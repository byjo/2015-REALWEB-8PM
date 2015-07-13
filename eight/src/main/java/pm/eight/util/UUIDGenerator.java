package pm.eight.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class UUIDGenerator {
	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String createImageUUIDWithDate() {
		DateFormat df = new SimpleDateFormat("yyMMdd", Locale.KOREAN);
		String beautifulToday = df.format(new Date());
		return "img-"+beautifulToday + "-" + UUIDGenerator.createUUID();
	}
}
