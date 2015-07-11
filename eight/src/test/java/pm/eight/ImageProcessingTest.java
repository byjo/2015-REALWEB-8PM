package pm.eight;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class ImageProcessingTest {

	String sUrl;
	URL url;
	String targetPath;
	
	@Before
	public void setUp() throws MalformedURLException {
		sUrl = "http://thumb.comic.naver.net/webtoon/621494/thumbnail/title_thumbnail_20140418115703_t83x90.jpg";
		url = new URL(sUrl);
		targetPath = "resources/images/test/test.jpg";
	}
//	제대로 이미지 못데려옴...
//	@Test
//	public void test() throws IOException {
//		BufferedImage b = ImageIO.read(url);
//		File outputfile = new File(targetPath);
//		ImageIO.write(b, "jpg", outputfile);
//	}

	@Test
	public void testSTREAM() throws Exception {
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(targetPath);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}
	
}
