package anla.Network.urlConnection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
/**
 * 测试http中的head请求头，
 * 它告诉服务器只返回http首部，不用实际发送文件，最常用的是
 * 检查最后一次缓存后是否有修改。
 * @author anla7856
 *
 */
public class LastModified {
	public static void main(String[] args) {
		try {
			URL u = new URL("http://www.baidu.com");
			HttpURLConnection http = (HttpURLConnection) u.openConnection();
			http.setRequestMethod("HEAD");
			System.out.println(u + "was last modified at "+ new Date(http.getLastModified()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
