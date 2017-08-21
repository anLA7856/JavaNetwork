package anla.Network.socket;

import java.net.Socket;
/**
 * 查看指定主机前1024个端口中哪些安装有tcp服务器。
 * @author anla7856
 *
 */
public class LowPortScanner {
	public static void main(String[] args) {
		String host = "localhost";
		for(int i = 1;i < 1024;i++){
			try {
				Socket s = new Socket();
				System.out.println("This is a server on port "+i+" of"+host);
				s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
