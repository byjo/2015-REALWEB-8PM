package pm.eight.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileIO {
	public static Boolean readAndWrite(String linkFrom, String targetPath){
		
		try {
			URL url = new URL(linkFrom);
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
			e.printStackTrace();
			return false;
		}

		return true;
		
	}

}
