package pm.eight;
import static org.junit.Assert.*;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;


public class CrawlerTest {

	@Test
	public void test() throws IOException {
		Document doc = Jsoup.connect("http://comic.naver.com/webtoon/weekday.nhn").get();
		Elements elUls = doc.select(".list_area ul");
		Elements elAll = elUls.select("li > div.thumb > a");
//		Elements elImages = elAll.select("img[src]");
//		Elements elImages = doc.select(".list_area ul li > div.thumb a img[src]");
		
		for (Element elOne : elAll) {
//			id
//			title
//			요일
//			에피소드 리스트 링
//			thumnail 조그만 이미지
			  Element elImage = elOne.select("img[src]").first();
			  String linkHref = elImage.attr("src");
			  String comicHref = elImage.attr("src");
			  String comicSrc = saveImage(linkHref);
			  String comicName = elImage.attr("title");
			  
			  System.out.println(elOne);
			  System.out.println(linkHref);
//			  String linkText = elImage.text();
//			  System.out.println(linkHref);
		}
	}

	private String saveImage(String linkHref) {
		// TODO Auto-generated method stub
		return null;
	}

}
