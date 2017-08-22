package anla.Network.serverSocket;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.*;
/**
 * 模拟一个较为完整的http服务器。
 * 
 * 通过传入参数来启动。参数包括一个目录（模拟为web系统的根目录），端口（推荐非root设为1024以上）
 * 
 * 启动后，在浏览器输入http://localhost:1313，如果刚刚目录下有一个index.html，则可以显示，
 * 否则提示没有文件，即404.
 * @author anla7856
 *
 */
public class JHTTP {

	private static final Logger logger = Logger.getLogger(JHTTP.class
			.getCanonicalName());
	private static final int NUM_THREADS = 50;
	private static final String INDEX_FILE = "index.html";

	private final File rootDirectory;
	private final int port;

	public JHTTP(File rootDirectory, int port) throws IOException {

		if (!rootDirectory.isDirectory()) {
			throw new IOException(rootDirectory
					+ " does not exist as a directory");
		}
		this.rootDirectory = rootDirectory;
		this.port = port;
	}

	public void start() throws IOException {
		ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
		try (ServerSocket server = new ServerSocket(port)) {
			logger.info("Accepting connections on port "
					+ server.getLocalPort());
			logger.info("Document Root: " + rootDirectory);

			while (true) {
				try {
					Socket request = server.accept();
					Runnable r = new RequestProcessor(rootDirectory,
							INDEX_FILE, request);
					pool.submit(r);
				} catch (IOException ex) {
					logger.log(Level.WARNING, "Error accepting connection", ex);
				}
			}
		}
	}

	public static void main(String[] args) {

		// get the Document root
		File docroot;
		try {
			docroot = new File(args[0]);
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Usage: java JHTTP docroot port");
			return;
		}

		// set the port to listen on
		int port;
		try {
			port = Integer.parseInt(args[1]);
			if (port < 0 || port > 65535)
				port = 80;
		} catch (RuntimeException ex) {
			port = 80;
		}

		try {
			JHTTP webserver = new JHTTP(docroot, port);
			webserver.start();
		} catch (IOException ex) {
			logger.log(Level.SEVERE, "Server could not start", ex);
		}
	}
}