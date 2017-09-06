package anla.VM.outOfMemoryError;
import sun.misc.*;

import java.lang.reflect.Field;

/**
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * @author anla7856
 * 直接通过反射获取unsafe实例，进行内存分配（
 * Unsafe类的getUnsafe方法限制了只有引导类加载器才会返回实例，也就是设计者希望只有
 * rt.jar中的类才能使用unsafe功能。真正申请内存的方法是unsafe.allocateMemory）
 * 
 * 运行是电脑完全卡死。。。。
 */
public class DirectMemoryOOM {
	private static final int _1MB = 1024*1024;
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while(true){
			unsafe.allocateMemory(_1MB);
		}
	}
}
