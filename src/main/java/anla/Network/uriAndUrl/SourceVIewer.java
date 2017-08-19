package anla.Network.uriAndUrl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
/**
 * 使用url下载一个页面，
 * 注意str必须加上http，否则会报错说没有协议
 * @author anla7856
 *
 */
public class SourceVIewer {
	public static void main(String[] args) {
		String str = "http://www.baidu.com";
		InputStream in = null;
		try {
			URL u = new URL(str);
			in = u.openStream();
			in = new BufferedInputStream(in);
			
			Reader r = new InputStreamReader(in);
			int c;
			while((c = r.read()) != -1){
				System.out.print((char)c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
