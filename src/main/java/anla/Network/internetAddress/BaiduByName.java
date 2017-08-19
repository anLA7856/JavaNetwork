package anla.Network.internetAddress;

import java.net.InetAddress;
/**
 * 通过域名地址获得ip地址。
 * @author anla7856
 *
 */
public class BaiduByName {
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("www.baidu.com");
			InetAddress address1 = InetAddress.getByName("163.177.151.110");
			System.out.println(address);
			System.out.println(address1.getHostName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
