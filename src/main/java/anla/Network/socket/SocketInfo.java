package anla.Network.socket;

import java.net.Socket;
/**
 * 本地端口连接远程端口相关信息
 * 输出：
 * Connected to www.baidu.com/163.177.151.109 on port 80 from port 51730 of /192.168.1.107
Connected to www.taobao.com/163.177.63.95 on port 80 from port 57400 of /192.168.1.107
 * @author anla7856
 *
 */
public class SocketInfo {
	public static void main(String[] args) {
		for(String host : args){
			try {
				Socket thisSocket = new Socket(host,80);
				System.out.println("Connected to "+ thisSocket.getInetAddress()
						+ " on port "+ thisSocket.getPort() + " from port "
						+ thisSocket.getLocalPort() + " of "
						+ thisSocket.getLocalAddress());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
