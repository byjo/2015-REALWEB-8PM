package pm.eight.util;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import pm.eight.dto.ComicPageDTO;
import pm.eight.dto.EpisodePageDTO;

@Component
public class WebtoonCrawler {

	private static final String REFERER = "http://imgcomic.naver.com/";
	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";
	private static final String NAVER_WEBTOON_URL = "http://comic.naver.com/";
	private static final int INITIAL_HEIGHT = 0;

	public EpisodePageDTO crawlEpisodePage(String episodePageLink) throws IOException {
		EpisodePageDTO episodeDTO = new EpisodePageDTO();
		Document doc = Jsoup.connect(episodePageLink).get();
		episodeDTO.setAmount(crawlImageTotalHeight(doc));
		episodeDTO.setPublishingDay(getPublishingDay(doc));
		return episodeDTO;
	}

	private String getPublishingDay(Document doc) {
		Elements dateElement = doc.select("#content .date");
		return dateElement.text();
	}

	private long crawlImageTotalHeight(Document doc) throws MalformedURLException, IOException {
		long totalHeight = INITIAL_HEIGHT;
		Elements imgTags = doc.select(".wt_viewer img");
		for(Element imgTag: imgTags){
			totalHeight+=extractImageHeight(imgTag);
		}
		return totalHeight;
	}

	private long extractImageHeight(Element imgTag) throws MalformedURLException, IOException {
		String src = imgTag.attr("src");
		URL url = new URL(src);
		URLConnection con = url.openConnection();
		con.setRequestProperty("Referer", REFERER);
		con.setRequestProperty("User-Agent",USER_AGENT);
		Image image = ImageIO.read(con.getInputStream());
		return image.getHeight(null);
	}

	public ComicPageDTO crawlComicPage(String comicPageLink) throws IOException {
		ComicPageDTO comicDTO = new ComicPageDTO();
		Document doc = Jsoup.connect(comicPageLink).get();
		comicDTO.setLatestEpisodeLink(crawlLatestEpisodeLink(doc));
		return comicDTO;
	}

	private String crawlLatestEpisodeLink(Document doc) {
		Element aTag = doc.select(".title a").first();
		String latestEpisode = aTag.attr("href");
		return NAVER_WEBTOON_URL+latestEpisode;
	}
	
	
}
