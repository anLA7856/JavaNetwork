package anla.Network.socket;

import java.net.*;
import java.io.*;
/**
 * 通过java网络编程，访问whois.internic.net
 * 从而来查询个人信息
 * 
 * whois是rfc 954中定义的一个简单目录服务协议
 * whois客户端联结到多个中心服务器中的一个，请求一个或多个
 * 目录信息，它通常会提供一个电话，电子邮件和一个传统邮件地址。
 * 
 * @author anla7856
 *
 */
public class Whois {

	public final static int DEFAULT_PORT = 43;
	public final static String DEFAULT_HOST = "whois.internic.net";

	private int port = DEFAULT_PORT;
	private InetAddress host;

	public Whois(InetAddress host, int port) {
		this.host = host;
		this.port = port;
	}

	public Whois(InetAddress host) {
		this(host, DEFAULT_PORT);
	}

	public Whois(String hostname, int port) throws UnknownHostException {
		this(InetAddress.getByName(hostname), port);
	}

	public Whois(String hostname) throws UnknownHostException {
		this(InetAddress.getByName(hostname), DEFAULT_PORT);
	}

	public Whois() throws UnknownHostException {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}

	// Items to search for
	public enum SearchFor {
		ANY("Any"), NETWORK("Network"), PERSON("Person"), HOST("Host"), DOMAIN(
				"Domain"), ORGANIZATION("Organization"), GROUP("Group"), GATEWAY(
				"Gateway"), ASN("ASN");

		private String label;

		private SearchFor(String label) {
			this.label = label;
		}
	}

	// Categories to search in
	public enum SearchIn {
		ALL(""), NAME("Name"), MAILBOX("Mailbox"), HANDLE("!");

		private String label;

		private SearchIn(String label) {
			this.label = label;
		}
	}

	public String lookUpNames(String target, SearchFor category,
			SearchIn group, boolean exactMatch) throws IOException {

		String suffix = "";
		if (!exactMatch)
			suffix = ".";

		String prefix = category.label + " " + group.label;
		String query = prefix + target + suffix;

		Socket socket = new Socket();
		try {
			SocketAddress address = new InetSocketAddress(host, port);
			socket.connect(address);
			Writer out = new OutputStreamWriter(socket.getOutputStream(),
					"ASCII");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream(), "ASCII"));
			out.write(query + "\r\n");
			out.flush();

			StringBuilder response = new StringBuilder();
			String theLine = null;
			while ((theLine = in.readLine()) != null) {
				response.append(theLine);
				response.append("\r\n");
			}
			return response.toString();
		} finally {
			socket.close();
		}
	}

	public InetAddress getHost() {
		return this.host;
	}

	public void setHost(String host) throws UnknownHostException {
		this.host = InetAddress.getByName(host);
	}
}