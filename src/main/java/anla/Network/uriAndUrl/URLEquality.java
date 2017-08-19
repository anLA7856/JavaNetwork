package anla.Network.uriAndUrl;

import java.net.URL;
/**
 * 当两个url都只想相同的主机端口和路径上的相同资源是，
 * 而且有相同的片段标识符和查询字符串时，才认为两个
 * url是相等的。
 * 
 * @author anla7856
 *
 */
public class URLEquality {
	public static void main(String[] args) {
		try {
			URL www = new URL("http://www.ibiblio.org/");
			URL ibiblio = new URL("http://ibiblio.org/");
			
			if(ibiblio.equals(www)){
				System.out.println("same");
			}else{
				System.out.println("not same");
			}
			
			//public boolean sameFIle(URL other);
			URL u1 = new URL("http://www.anla.com/welcome.html#wc"); 
			URL u2 = new URL("http://www.anla.com/welcome.html#wd"); 
			if(u1.equals(u2)){
				System.out.println("u1 equals u2");
			}else{
				System.out.println("u1 not equals u2");
			}
			
			if(u1.sameFile(u2)){
				System.out.println("u1 sameFile u2");
			}else{
				System.out.println("u1 not sameFile u2");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
