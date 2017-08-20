package anla.Network.urlConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.util.List;
import java.util.Map;

public class SImpleCacheResponse extends CacheResponse{


	@Override
	public Map<String, List<String>> getHeaders() throws IOException {
		return null;
	}

	@Override
	public InputStream getBody() throws IOException {
		return null;
	}

}
