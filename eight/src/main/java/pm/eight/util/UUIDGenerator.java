package pm.eight.util;

import java.util.UUID;

public class UUIDGenerator {
	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
