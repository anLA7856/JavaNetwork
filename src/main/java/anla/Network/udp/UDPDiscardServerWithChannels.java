package anla.Network.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * 利用channels来实现udp的discardserver。
 * @author anla7856
 *
 */
public class UDPDiscardServerWithChannels {

	public final static int PORT = 9;
	public final static int MAX_PACKET_SIZE = 65507;

	public static void main(String[] args) {

		try {
			DatagramChannel channel = DatagramChannel.open();
			DatagramSocket socket = channel.socket();
			SocketAddress address = new InetSocketAddress(PORT);
			socket.bind(address);
			ByteBuffer buffer = ByteBuffer.allocateDirect(MAX_PACKET_SIZE);
			while (true) {
				//读入到缓冲区中。
				SocketAddress client = channel.receive(buffer);
				buffer.flip();
				System.out.print(client + " says ");
				while (buffer.hasRemaining())
					System.out.write(buffer.get());
				System.out.println();
				buffer.clear();
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}