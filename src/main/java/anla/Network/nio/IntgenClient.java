package anla.Network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SocketChannel;
/**
 * 用于从管道中读取服务器端的数据。
 * 这里主要是Intgenserver的数据。
 * 会循环输出很多数据。
 * @author anla7856
 *
 */
public class IntgenClient {

	public static int DEFAULT_PORT = 1919;

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Usage: java IntgenClient host [port]");
			return;
		}

		int port;
		try {
			port = Integer.parseInt(args[1]);
		} catch (RuntimeException ex) {
			port = DEFAULT_PORT;
		}

		try {
			SocketAddress address = new InetSocketAddress(args[0], port);
			SocketChannel client = SocketChannel.open(address);
			ByteBuffer buffer = ByteBuffer.allocate(4);
			IntBuffer view = buffer.asIntBuffer();

			//读到缓冲区，再由缓冲区读到actual输出。
			for (int expected = 0;; expected++) {
				client.read(buffer);
				int actual = view.get();
				buffer.clear();
				view.rewind();

				if (actual != expected) {
					System.err.println("Expected " + expected + "; was "
							+ actual);
					break;
				}
				System.out.println(actual);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}