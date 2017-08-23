package anla.Network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * udp数据包相关例子。
 * @author anla7856
 *
 */
public class DaytimeUDPClient {

	private final static int PORT = 13;
	private static final String HOSTNAME = "localhost";

	public static void main(String[] args) {
		//也就是请求java为你随机选择一个可用的端口。
		try (DatagramSocket socket = new DatagramSocket(0)) {
			socket.setSoTimeout(10000);
			InetAddress host = InetAddress.getByName(HOSTNAME);
			DatagramPacket request = new DatagramPacket(new byte[1], 1, host,
					PORT);
			DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
			socket.send(request);
			socket.receive(response);
			String result = new String(response.getData(), 0,
					response.getLength(), "ASCII");
			System.out.println(result);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}