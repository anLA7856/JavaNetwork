package anla.Network.multicastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 意思就是，加入某一个组播ip的组，里面，然后向所有组内成员发送数据。
 * 可以和MulticastSniffer结合使用。
 * 先输入一个组播地址和端口启动sender类：如239.255.255.250 1900
 * 在将sniffer类启动，同样的端口和ip：239.255.255.250 1900
 * @author anla7856
 *
 */
public class MulticastSender {

	public static void main(String[] args) {

		InetAddress ia = null;
		int port = 0;
		byte ttl = (byte) 1;

		// 读取地址
		try {
			ia = InetAddress.getByName(args[0]);
			port = Integer.parseInt(args[1]);
			if (args.length > 2)
				ttl = (byte) Integer.parseInt(args[2]);
		} catch (NumberFormatException | IndexOutOfBoundsException
				| UnknownHostException ex) {
			System.err.println(ex);
			System.err
					.println("Usage: java MulticastSender multicast_address port ttl");
			System.exit(1);
		}

		byte[] data = "Here's some multicast data\r\n".getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);

		try (MulticastSocket ms = new MulticastSocket()) {
			ms.setTimeToLive(ttl);
			ms.joinGroup(ia);
			for (int i = 1; i < 10; i++) {
				ms.send(dp);
			}
			ms.leaveGroup(ia);
		} catch (SocketException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}