package anla.VM.classloader;

class ConstClass1 {
	static {
		System.out.println("ConstClass init!");
	}

	public static final String HELLOWORLD = "hello world";
}

public class ConstClass {
	public static void main(String[] args) {
		//常量在编译就诶段会存入调用类的常量池中，本质是没有直接引用到定义常量的类，因此不会触发定义
		//常量的初始化。
		System.out.println(ConstClass1.HELLOWORLD);
	}
}
