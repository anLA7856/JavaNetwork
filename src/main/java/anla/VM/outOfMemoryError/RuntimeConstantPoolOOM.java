package anla.VM.outOfMemoryError;

import java.util.ArrayList;
import java.util.List;
/**
 * VM Args:-XX:PermSize=10M -XX:MaxPermSize=10M
 * @author anla7856
 * 方法区（hotspot虚拟机中永久代）的溢出测试
 * 方法区溢出还有使用CGLib的情况：
 * 增强的类越多，就需要越大的方法区来保证动态生成的Class可以被加载如内存。
 * 
 * 除此之外还有，大量JSP或者动态产生jsp文件的应用，
 * 基于OSGi的应用。
 */
public class RuntimeConstantPoolOOM {
	
	public static void main(String[] args) {
		//使用list保持这常量池应用，避免gc回收常量池
		List<String> list = new ArrayList<String>();
		int i = 0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
