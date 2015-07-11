package pm.eight.checker;

import java.io.IOException;

import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.jsoup.nodes.Element;

import pm.eight.enums.WebtoonStateType;

import java.util.List;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:resources/crawler-servlet.xml" })
public class PublishCheckerTest {
	
	PublishChecker publishChecker = new PublishChecker(); // bean으로 등록해두었는데.. autowired가 잘 안됨..
	
	@Test
	public void test() throws IOException {
		Map<String, WebtoonStateType> epStateList = publishChecker.getEpisodeStatusList();
		for( String key : epStateList.keySet() ){
			System.out.println( String.format("title : %s, state : %s", key, epStateList.get(key)) );
		}
	}

}
