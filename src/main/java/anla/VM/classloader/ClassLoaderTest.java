package anla.VM.classloader;

import java.io.InputStream;
/**
 * 每一个类加载器都拥有一个独立的类名称空间，
 * 都有一个即使两个类来源与同一个class，被同一个虚拟机加载，
 * 只要加载的类加载器不同，那这两个类比不相等。
 * @author anla7856
 *
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		ClassLoader loader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name)
					throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if(is == null){
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0,b.length);
				} catch (Exception e) {
					throw new ClassNotFoundException(name);
				}
				
			}
		};
		
		Object obj = loader.loadClass("anla.VM.classloader.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof anla.VM.classloader.ClassLoaderTest);
	}
}
