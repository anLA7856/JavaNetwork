package anla.VM.remoteExec;

import java.lang.reflect.Method;

/**
 * javaclass执行工具
 * 
 * @author anla7856
 *
 */
public class JavaClassExecuter {

	public static String execute(byte[] classByte) {
		HackSystem.clearBuffer();
		ClassModifier cm = new ClassModifier(classByte);

		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System",
				"anla/VM/remoteExec/HackSystem");

		HotSwapClassLoader loader = new HotSwapClassLoader();

		Class clazz = loader.loadByte(modiBytes);

		try {
			Method method = clazz.getMethod("main",
					new Class[] { String[].class });
			method.invoke(null, new String[] { null });
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return HackSystem.getBufferString();
	}
}
