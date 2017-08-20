package anla.Network.urlConnection;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
/**
 * 下载固定的web文件并保存到磁盘中。
 * @author anla7856
 *
 */
public class BinarySaver {
	
	
	public static void saveBinaryFile(URL u) throws IOException{
		URLConnection uc = u.openConnection();
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
		
		if(contentType.startsWith("text/") || contentLength == -1){
			throw new IOException("This is not a binary file.");
		}
		
		InputStream raw = uc.getInputStream();
		
		try {
			InputStream in = new BufferedInputStream(raw);
			byte[] data = new byte[contentLength];
			int offset = 0;
			while(offset < contentLength){
				int bytesRead = in.read(data,offset,data.length-offset);
				if(bytesRead == -1) break;
				offset += bytesRead;
			}
			if(offset != contentLength){
				throw new IOException("Only read "+ offset +" bytes; Expected "+ contentLength + " bytes");
				
			}
			String fileName = u.getFile();
			fileName = fileName.substring(fileName.lastIndexOf('/')+1);
			try {
				FileOutputStream fout = new FileOutputStream(fileName);
				fout.write(data);
				fout.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
