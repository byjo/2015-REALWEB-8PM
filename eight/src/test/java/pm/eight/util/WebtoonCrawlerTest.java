package pm.eight.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pm.eight.dto.EpisodePageDTO;

public class WebtoonCrawlerTest {

	WebtoonCrawler crawler;
	
	@Before
	public void setup() {
		crawler = new WebtoonCrawler();
	}

	@Test
	public void test() throws IOException {
		EpisodePageDTO result = crawler.crawlEpisodePage("http://comic.naver.com/webtoon/detail.nhn?titleId=570503&no=92&weekday=thu");
		System.out.println(result.getPublishingDay());
		//TODO: 언제나 테스트 통과하도록 작성해야 함
	}

}
