package anla.Network.uriAndUrl;

import java.net.URLEncoder;

public class QueryString {
	private StringBuilder query = new StringBuilder();
	
	public synchronized void add(String name,String value){
		query.append('&');
		encode(name,value);
	}
	
	public synchronized void encode(String name,String value){
		try {
			query.append(URLEncoder.encode(name,"UTF-8"));
			query.append('=');
			query.append(URLEncoder.encode(value, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized String getQuery(){
		return query.toString();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
