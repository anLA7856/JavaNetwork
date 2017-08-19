package anla.Network.internetAddress;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
/**
 * 获取本机以太网接口。
 * @author anla7856
 *
 */
public class MyNetworkInterface {
	public static void main(String[] args) {
		try {
			NetworkInterface ni = NetworkInterface.getByName("eth1");
			System.out.println(ni);
			
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while(interfaces.hasMoreElements()){
				NetworkInterface ni1 = interfaces.nextElement();
				System.out.println(ni1);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
