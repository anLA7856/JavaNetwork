package anla.VM.byteEngine;
/**
 * 静态类和普通内部类，都是返回 hello guy hello guy
 * hello guy
 * hello guy
 * @author anla7856
 *
 */
public class StaticDispatch {
	static abstract class Human {

	}

	static class Man extends Human {

	}

	static class Woman extends Human {

	}

	public void sayHello(Human guy) {
		System.out.println("hello guy");
	}

	public void sayHello(Man guy) {
		System.out.println("hello gentleman");
	}

	public void sayHello(Woman guy) {
		System.out.println("hello,lady");
	}

	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		StaticDispatch sd = new StaticDispatch();
		sd.sayHello(man);
		sd.sayHello(woman);
	}
}
