package pm.eight.util;


import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;

import pm.eight.util.FileIO;

public class FileIOTest {

	String url;
	String targetPath;
	
	@Before
	public void setUp() throws MalformedURLException {
		url = "http://thumb.comic.naver.net/webtoon/621494/thumbnail/title_thumbnail_20140418115703_t83x90.jpg";
		targetPath = "resources/images/test/test.jpg";
	}

	@Test
	public void testSTREAM() throws Exception {
		FileIO.readAndWrite(url, targetPath);
	}
}
