package anla.Network.urlConnection;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
/**
 * 用正确的字符集来下载一个页面。
 * @author anla7856
 *
 */
public class EncodingAwareSourceViewer {
	public static void main(String[] args) {
		try {
			String str = "http://www.baidu.com";
			String encoding = "ISO-8859-1";
			URL u = new URL(str);
			URLConnection uc = u.openConnection();
			String contentType = uc.getContentType();
			int encodingStart = contentType.indexOf("charset=");
			if(encodingStart != -1){
				encoding = contentType.substring(encodingStart+8);
			}
			
			InputStream in = new BufferedInputStream(uc.getInputStream());
			Reader r = new InputStreamReader(in,encoding);
			int c;
			while((c = r.read())!= -1){
				System.out.print((char)c);
			}
			
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
