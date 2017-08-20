package anla.Network.urlConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import anla.Network.uriAndUrl.QueryString;

public class FormPoster {
	private URL url;
	private QueryString query = new QueryString();
	
	public FormPoster (URL url){
		if(!url.getProtocol().toLowerCase().startsWith("http")){
			throw new IllegalArgumentException("Posting only works for http URLs");
		}
		this.url = url;
	}
	
	public void add(String name,String value){
		query.add(name, value);
	}
	
	public URL getURL(){
		return this.url;
	}
	
	public InputStream post() throws IOException{
		URLConnection uc = url.openConnection();
		uc.setDoOutput(true);
		try (OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(),"UTF-8")){
			out.write(query.toString());
			out.write("\r\n");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uc.getInputStream();
	}
	
	
}
