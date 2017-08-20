package anla.Network.uriAndUrl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
/**
 * 每个运行的虚拟机都只有一个proxyselector对象，用来确定不同联结的代理服务器。
 * 使用如下方法可以改变：
 * ProxySelector selector = new LocalProxySelector();
 * ProxySelector.setDefault(selector);
 * 此时虚拟机打开的所有联结都要询问他将要使用的正确代理。
 * @author anla7856
 *
 */
public class LocalProxySelector extends ProxySelector {
	private List<URI> failed = new ArrayList<URI>();

	@Override
	public List<Proxy> select(URI uri) {
		List<Proxy> result = new ArrayList<Proxy>();
		if(failed.contains(uri) || !"http".equalsIgnoreCase(uri.getScheme())){
			result.add(Proxy.NO_PROXY);
		}else{
			SocketAddress proxyAddress = new InetSocketAddress("proxy.example.com", 8080);
			Proxy proxy = new Proxy(Proxy.Type.HTTP,proxyAddress);
			result.add(proxy);
		}
		return result;
	}

	@Override
	public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
		failed.add(uri);
	}
	
	
}
