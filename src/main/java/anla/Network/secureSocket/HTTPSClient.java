package anla.Network.secureSocket;

import java.io.*;
import javax.net.ssl.*;

/**
 * 就相当与一个浏览器，是以https的方式访问的， 最后输出网页源代码， 例如args的第一个参数为,www.baidu.com
 * 
 * @author anla7856
 *
 */
public class HTTPSClient {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Usage: java HTTPSClient2 host");
			return;
		}

		int port = 443; // default https port
		String host = args[0];

		SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory
				.getDefault();
		SSLSocket socket = null;
		try {
			socket = (SSLSocket) factory.createSocket(host, port);

			// 启用所有密码组
			String[] supported = socket.getSupportedCipherSuites();
			socket.setEnabledCipherSuites(supported);

			Writer out = new OutputStreamWriter(socket.getOutputStream(),
					"UTF-8");
			// https在get请求中，需要完整的url
			out.write("GET http://" + host + "/ HTTP/1.1\r\n");
			out.write("Host: " + host + "\r\n");
			out.write("\r\n");
			out.flush();

			// 读取响应
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			// 读取首部
			String s;
			while (!(s = in.readLine()).equals("")) {
				System.out.println(s);
			}
			System.out.println();

			// 读取长度。
			String contentLength = in.readLine();
			int length = Integer.MAX_VALUE;
			try {
				length = Integer.parseInt(contentLength.trim(), 16);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			System.out.println(contentLength);

			int c;
			int i = 0;
			while ((c = in.read()) != -1 && i++ < length) {
				System.out.write(c);
			}

			System.out.println();
		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
			}
		}
	}
}