package anla.Network.serverSocket;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
/**
 * 建立一个server的服务器，
 * 注意端口port，如果再linux下，1～1024必须要root用户来运行该程序。
 * 如果想普通运行，则端口改为1313即可。
 * 
 * 试验方式，打开terminal，输入telnet localhost 1313，即可查看结果
 * @author anla7856
 *
 */
public class DaytimeServer {
	public final static int PORT = 1313;
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(PORT)) {
			while(true){
				try (Socket connection = server.accept()){
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString()+"\r\n");
					out.flush();
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
