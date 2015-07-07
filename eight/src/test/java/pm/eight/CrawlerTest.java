package pm.eight;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import pm.eight.model.Comic;

public class CrawlerTest {
	List<Comic> comics = new LinkedList();

	@Test
	public void test() throws IOException {
		Document doc = Jsoup.connect(
				"http://comic.naver.com/webtoon/weekday.nhn").get();
		Elements elUls = doc.select(".list_area ul");
		// Elements elAll = elUls.select("li > div.thumb > a");
		// Elements elImages = elAll.select("img[src]");
		// Elements elImages =
		// doc.select(".list_area ul li > div.thumb a img[src]");

		List<String> list = Arrays.asList("MON", "THU", "WED", "THU", "FRI",
				"SAT", "SUN");

		for (int i = 0; i < list.size(); i++) {
			put(elUls.select("li > div.thumb > a"), list.get(i));
		}

	}

	private void put(Elements elAll, String comicWeek) {
		for (Element elOne : elAll) {
			Element elImage = elOne.select("img[src]").first();
			String comicLink = elOne.attr("href");
			String comicImgUrl = saveImage(elImage.attr("src"));
			String comicTitle = elImage.attr("title");
			Comic comic = new Comic(comicTitle, comicWeek, comicLink, comicImgUrl);
			comics.add(comic);
			System.out.println(comic);
		}
	}

	private String saveImage(String linkHref) {
		return linkHref;
	}

}
