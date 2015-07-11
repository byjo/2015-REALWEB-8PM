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

import pm.eight.dto.EpisodePageDTO;

public class WebtoonCrawler {

	private static final String REFERER = "http://imgcomic.naver.com/";
	private static final String USERAGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31";
	private static final int INITIALHEIGHT = 0;

	public EpisodePageDTO crawlEpisodePage(String episodeUrl) throws IOException {
		EpisodePageDTO episodeDTO = new EpisodePageDTO();
		Document doc = Jsoup.connect(episodeUrl).get();
		episodeDTO.setAmount(getImageTotalHeight(doc));
//		TODO: 어디에서 링크를 저장할지 결정해야 함
//		episodeDTO.setLink(link); 
		episodeDTO.setPublishingDay(getPublishingDay(doc));
		return episodeDTO;
	}

	private String getPublishingDay(Document doc) {
		Elements dateElement = doc.select("#content .date");
		return dateElement.text();
	}

	private long getImageTotalHeight(Document doc) throws MalformedURLException, IOException {
		long totalHeight = INITIALHEIGHT;
		Elements imgTags = doc.select(".wt_viewer img");
		for(Element imgTag: imgTags){
			totalHeight+=getImageHeight(imgTag);
		}
		return totalHeight;
	}

	private long getImageHeight(Element imgTag) throws MalformedURLException, IOException {
		String src = imgTag.attr("src");
		URL url = new URL(src);
		URLConnection con = url.openConnection();
		con.setRequestProperty("Referer", REFERER);
		con.setRequestProperty("User-Agent",USERAGENT);
		Image image = ImageIO.read(con.getInputStream());
		return image.getHeight(null);
	}
}
