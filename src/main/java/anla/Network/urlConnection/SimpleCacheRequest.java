package anla.Network.urlConnection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;
/**
 * 属于Java的web缓存。
 * 
 * 将request的数据缓存起来
 * 
 * 一个基本的cacherequest子类。可以从getdata方法中获取数据。
 * @author anla7856
 *
 */
public class SimpleCacheRequest extends CacheRequest{

	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	
	@Override
	public OutputStream getBody() throws IOException {
		return out;
	}

	@Override
	public void abort() {
		out.reset();
	}
	
	public byte[] getData(){
		if(out.size() == 0){
			return null;
		}else{
			return out.toByteArray();
		}
	}

}
