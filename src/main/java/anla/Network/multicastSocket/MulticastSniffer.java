package anla.Network.multicastSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
/**
 * 加入到这个组，也就是能够接收到由这个组播ip地址
 * 发送的udp数据包。
 * 接受然后显示。
 * 意思就是，加入某一个组播ip的组，里面，然后向所有组内成员接受数据。
 * 先输入一个组播地址和端口启动sender类：如239.255.255.250 1900
 * 在将sniffer类启动，同样的端口和ip：239.255.255.250 1900
 *
 * @author anla7856
 *
 */
public class MulticastSniffer {

	public static void main(String[] args) {

		InetAddress group = null;
		int port = 0;

		// 从命令行中读取地址。
		try {
			group = InetAddress.getByName(args[0]);
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException
				| UnknownHostException ex) {
			System.err
					.println("Usage: java MulticastSniffer multicast_address port");
			System.exit(1);
		}

		MulticastSocket ms = null;
		try {
			ms = new MulticastSocket(port);
			//加入组。
			ms.joinGroup(group);

			byte[] buffer = new byte[8192];
			while (true) {
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				ms.receive(dp);
				String s = new String(dp.getData(), "8859_1");
				System.out.println(s);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			if (ms != null) {
				try {
					ms.leaveGroup(group);
					ms.close();
				} catch (IOException ex) {
				}
			}
		}
	}
}