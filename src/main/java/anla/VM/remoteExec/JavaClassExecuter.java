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

		/**
		 * 把原生的System类完全替换为自己实现的HackSystem。
		 */
		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System",
				"anla/VM/remoteExec/HackSystem");

		HotSwapClassLoader loader = new HotSwapClassLoader();

		Class clazz = loader.loadByte(modiBytes);

		try {
			//利用反射执行main方法。
			Method method = clazz.getMethod("main",
					new Class[] { String[].class });
			method.invoke(null, new String[] { null });
		} catch (Throwable e) {
			e.printStackTrace();
		}

		return HackSystem.getBufferString();
	}
}
