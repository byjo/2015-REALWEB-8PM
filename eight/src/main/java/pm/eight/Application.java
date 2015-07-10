package pm.eight;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {
	
	public static void main(String[] args) {
		String springConfig = "applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	}
}
