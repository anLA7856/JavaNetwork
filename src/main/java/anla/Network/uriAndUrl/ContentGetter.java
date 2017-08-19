package anla.Network.uriAndUrl;

import java.net.URL;

/**
 * 可以通过getcontent区加载图像，音频等。
 * @author anla7856
 *
 */
public class ContentGetter {
	public static void main(String[] args) {
		try {
			URL u = new URL("http://www.baidu.com");
			Object o = u.getContent();
			System.out.println("I got a "+o.getClass().getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
