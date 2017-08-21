package anla.Network.socket;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * 在linux中通过java来操作telnet。
 * 下面代码类似于命令行
 * telnet time.nist.gov 13
 * @author anla7856
 *
 */
public class DaytimeClient {
	public static void main(String[] args) {
		String hostname = args.length > 0? args[0]:"time.nist.gov";
		Socket socket = null;
		try {
			socket = new Socket(hostname, 13);
			socket.setSoTimeout(15000);
			InputStream is = socket.getInputStream();
			StringBuilder time = new StringBuilder();
			InputStreamReader reader = new InputStreamReader(is,"ASCII");
			
			for(int c = reader.read(); c!= -1;reader.read()){
				time.append((char)c);
			}
			System.out.println(time.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(socket != null){
					socket.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
