package pm.eight.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pm.eight.dto.EpisodePageDTO;

public class WebtoonCrawlerTest {

	private static final String TARGETURL = "http://comic.naver.com/webtoon/detail.nhn?titleId=570503&no=92&weekday=thu";
	WebtoonCrawler crawler;
	
	@Before
	public void setup() {
		crawler = new WebtoonCrawler();
	}

	@Test
	public void crawlEpisodePageTest() throws IOException {
		EpisodePageDTO episodeDTO = crawler.crawlEpisodePage(TARGETURL);
		assertEquals("2015.07.08", episodeDTO.getPublishingDay());
		assertEquals("47502", episodeDTO.getAmount());
	}

}
