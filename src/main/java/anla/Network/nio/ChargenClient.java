package anla.Network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
/**
 * 用于连接服务器的客户端。
 * 比如本包下有个chargenserver，启动后连接
 * 一旦成功，就会把server的返回的东西打印出来，就是95个可打印的字符。
 * 端口port指的是远程服务器的端口。
 * @author anla7856
 *
 */
public class ChargenClient {

	public static int DEFAULT_PORT = 19;

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Usage: java ChargenClient host [port]");
			return;
		}

		int port;
		try {
			port = Integer.parseInt(args[1]);
		} catch (RuntimeException ex) {
			port = DEFAULT_PORT;
		}

		try {
			//在某一个端指向某一个地址的通道。
			SocketAddress address = new InetSocketAddress(args[0], port);
			SocketChannel client = SocketChannel.open(address);

			//定义一个缓冲区，通到会从socket读取数据来填充这个缓冲区。
			ByteBuffer buffer = ByteBuffer.allocate(74);
			//将system.out封装到一个channel中。
			WritableByteChannel out = Channels.newChannel(System.out);

			/**
			 * 可以将读取的数据写入到system.out连接的输出通道中，不过，这样做之前，必须回绕（flip）缓冲区，
			 * 使得输出通到从开头而不是结尾开始写入。
			 */
			while (client.read(buffer) != -1) {
				buffer.flip();
				out.write(buffer);
				buffer.clear();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}