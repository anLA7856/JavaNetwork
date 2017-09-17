package anla.VM.remoteExec;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * 为javaclass劫持java.lang.system提供支持
 * 除了out和err外，其余的都直接转发给system处理
 * @author anla7856
 *
 */
public class HackSystem {
	public final static InputStream in = System.in;
	
	private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
	public final static PrintStream out = new PrintStream(buffer);
	
	public final static PrintStream err = out;
	
	public static String getBufferString(){
		return buffer.toString();
	}
	
	public static void clearBuffer(){
		buffer.reset();
	}
	
	public static void setSecurityManager(final SecurityManager sm){
		System.setSecurityManager(sm);
	}
	
	public static SecurityManager getSecurityManager(){
		return System.getSecurityManager();
	}
	
	public static long currentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length){
		System.arraycopy(src, srcPos, dest, destPos, length);
	}
	
	public static int identityHashCode(Object x){
		return System.identityHashCode(x);
	}
}
