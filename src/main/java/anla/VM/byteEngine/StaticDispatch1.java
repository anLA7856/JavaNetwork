package anla.VM.byteEngine;

/**
 * 静态类和普通内部类，都是返回 hello guy hello guy
 * hello guy
 * hello guy
 * @author anla7856
 *
 */
public class StaticDispatch1 {
	abstract class Human {

	}

	class Man extends Human {

	}

	class Woman extends Human {

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
		StaticDispatch1 sd = new StaticDispatch1();
		Human man = sd.new Man();
		Human woman = sd.new Woman();

		sd.sayHello(man);
		sd.sayHello(woman);
	}
}
