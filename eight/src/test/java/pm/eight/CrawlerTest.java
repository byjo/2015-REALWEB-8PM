package pm.eight;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import pm.eight.domain.Comic;
import pm.eight.util.UUIDGenerator;

public class CrawlerTest {
	List<Comic> memoryComicsTable;
	@Before
	public void setup() {
		memoryComicsTable = new LinkedList<Comic>();
	}

	@Test
	public void getThumbImage() throws IOException {
		Document doc = Jsoup.connect(
				"http://comic.naver.com/webtoon/weekday.nhn").get();
		Elements comicsUls = doc.select(".list_area ul");
		System.out.println(comicsUls);
		List<String> list = Arrays.asList("MON", "THU", "WED", "THU", "FRI",
				"SAT", "SUN");

		for (int i = 0; i < list.size(); i++) {
			makeComicFromComicsUl(comicsUls.get(i).select("li > div.thumb > a"), list.get(i));
		}

	}

	private void makeComicFromComicsUl(Elements comicsUls, String comicWeek) {
		for (Element comicsUl : comicsUls) {
			Element elImage = comicsUl.select("img[src]").first();
			String comicLink = comicsUl.attr("href");
			String comicTitle = elImage.attr("title");
			
			String comicImgUrl = saveImage(elImage.attr("src"));
			
			Comic comic = new Comic(comicTitle, comicWeek, comicLink,
					comicImgUrl);
			memoryComicsTable.add(comic);
		}
	}


	private String saveImage(String linkHref) {
		int exIndex = linkHref.lastIndexOf(".");
		DateFormat df = new SimpleDateFormat("yyMMdd", Locale.KOREAN);
		String beautifulToday = df.format(new Date());
		String extention = linkHref.substring(exIndex + 1);

		String targetPath;
		if (extention.length() == 3) {
			targetPath = "resources/images/comic/img-" + beautifulToday + "-"
					+ UUIDGenerator.createUUID() + "." + extention;
		}
		targetPath =  "resources/images/comic/img-" + beautifulToday + "-"
				+ UUIDGenerator.createUUID();
		
		String sUrl = linkHref;
		URL url;
		try {
			url = new URL(sUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(targetPath);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
