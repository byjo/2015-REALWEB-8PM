package pm.eight;

import org.springframework.context.support.GenericXmlApplicationContext;


public class Application {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String springConfig = "applicationContext.xml";
		new GenericXmlApplicationContext(springConfig);
	}
}
