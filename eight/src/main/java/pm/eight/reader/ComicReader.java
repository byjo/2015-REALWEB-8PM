package pm.eight.reader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import pm.eight.domain.Comic;
import pm.eight.util.FileIO;
import pm.eight.util.UUIDGenerator;

@Component
public class ComicReader implements ItemReader<List<Comic>> {
	List<String> list = Arrays.asList("MON", "THU", "WED", "THU", "FRI", "SAT",
			"SUN");

	@Override
	public List<Comic> read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		List<Comic> memoryComicsTable = new LinkedList<Comic>();

		Document doc = Jsoup.connect(
				"http://comic.naver.com/webtoon/weekday.nhn").get();
		Elements comicsUls = doc.select(".list_area ul");

		for (int i = 0; i < list.size(); i++) {
			makeComicFromComicsUl(
					comicsUls.get(i).select("li > div.thumb > a"), list.get(i),
					memoryComicsTable);
		}
		return memoryComicsTable;
	}

	private void makeComicFromComicsUl(Elements comicsUls, String comicWeek,
			List<Comic> memoryComicsTable) {
		for (Element comicsUl : comicsUls) {
			Element elImage = comicsUl.select("img[src]").first();
			String comicLink = comicsUl.attr("href");
			String comicTitle = elImage.attr("title");
			String comicImgUrl = UUIDGenerator.createImageUUIDWithDate();
			Comic comic = new Comic(comicTitle, comicWeek, comicLink,
					comicImgUrl);
			
			saveImage(elImage.attr("src"), comicImgUrl);
			memoryComicsTable.add(comic);
		}
	}

	private String saveImage(String linkFrom, String comicImgUrl) {
		int exIndex = linkFrom.lastIndexOf(".");
		String extention = linkFrom.substring(exIndex + 1);
		String targetPath = "resources/images/comic/" + comicImgUrl;
		if (extention.length() == 3) {
			targetPath += "." + extention;
		}

		FileIO.readAndWrite(linkFrom, targetPath);
		return null;
	}
}
