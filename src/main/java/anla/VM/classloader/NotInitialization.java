package anla.VM.classloader;
/**
 * 通过子类引用父类的静态字段，不会导致子类初始化
 * @author anla7856
 *
 */
public class NotInitialization {
	public static void main(String[] args) {
		//System.out.println(SubClass.value);
		//只会输出Super class init 123,而不会输出子类的SubClass init，说明子类没有初始化。
		
		SuperClass[] sca = new SuperClass[10];
		//啥也不会输出，通过数组定义来引用类，不会触发此类的初始化。
	}
}

class SuperClass{
	static {
		System.out.println("SuperClass init");
	}
	public static int value = 123;
}

class SubClass extends SuperClass{
	static{
		System.out.println("SubClass init");
	}
}


