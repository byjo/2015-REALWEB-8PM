package pm.eight.util;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import pm.eight.dto.ComicPageDTO;
import pm.eight.dto.EpisodePageDTO;

public class WebtoonCrawlerTest {

	private static final String EPISODE_PAGE_URL = "http://comic.naver.com/webtoon/detail.nhn?titleId=570503&no=92&weekday=thu";
	private static final String COMIC_PAGE_URL = "http://comic.naver.com/webtoon/list.nhn?titleId=183559&weekday=mon";
	WebtoonCrawler crawler;
	
	@Before
	public void setup() {
		crawler = new WebtoonCrawler();
	}

	@Test
	public void crawlEpisodePageTest() throws IOException {
		EpisodePageDTO episodeDTO = crawler.crawlEpisodePage(EPISODE_PAGE_URL);
		assertEquals("2015.07.08", episodeDTO.getPublishDay());
		assertEquals(47502, episodeDTO.getAmount());
	}
	
	@Test
	public void crawlComicPageTest() throws IOException {
		ComicPageDTO comicDTO = crawler.crawlComicPage(COMIC_PAGE_URL);
		assertEquals("http://comic.naver.com//webtoon/detail.nhn?titleId=183559&no=240&weekday=mon", comicDTO.getLatestLink());
	}

}
