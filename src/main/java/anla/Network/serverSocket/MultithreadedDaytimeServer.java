package anla.Network.serverSocket;

import java.net.*;
import java.io.*;
import java.util.Date;
/**
 * 使用多线程实现daytime服务器。
 * 也就是把发送回写的相关代码封装到一个线程中。
 * @author anla7856
 *
 */
public class MultithreadedDaytimeServer {

	public final static int PORT = 13;

	public static void main(String[] args) {
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try {
					Socket connection = server.accept();
					Thread task = new DaytimeThread(connection);
					task.start();
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println("Couldn't start server");
		}
	}

	private static class DaytimeThread extends Thread {

		private Socket connection;

		DaytimeThread(Socket connection) {
			this.connection = connection;
		}

		@Override
		public void run() {
			try {
				Writer out = new OutputStreamWriter(
						connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString() + "\r\n");
				out.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}