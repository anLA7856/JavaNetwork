package anla.Network.internetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获得本机主机名和ip
 * @author anla7856
 *
 */
public class MyAddress {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address);
	}
}
