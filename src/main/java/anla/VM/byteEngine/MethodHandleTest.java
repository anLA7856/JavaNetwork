package anla.VM.byteEngine;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
/**
 * 在jdk7中，由新家的invokedynamic指令以及java.lang.invoke包下面，
 * 可以构造如下类似函数指针或者委托的方法别名工具了。
 * 
 * 虽然反射也能够完成，但反射是模拟java代码层次的调用，
 * 而MethodHandle则是摸底字节码层次调用
 * @author anla7856
 *
 */
public class MethodHandleTest {
	static class ClassA{
		public void println(String s){
			System.out.println(s);
		}
	}
	
	private static MethodHandle getPrintlnMH(Object reveiver) throws Throwable{
		/*method，第一个参数为返回值，第二个参数及以后则为方法参数*/
		MethodType mt = MethodType.methodType(void.class,String.class);
		/**
		 * lookup用于在指定类中查找给定方法名称，方法类型，并且符合调用权限的方法句柄
		 * 因为这里调用的是一个虚方法，按照java语言规则，第一个参数是隐式的，代表该方法的接受着，即this指定对象
		 * 现在是用bindTo来绑定
		 */
		return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
	}
	
	public static void main(String[] args) throws Throwable {
		Object obj = System.currentTimeMillis() % 2 == 0? System.out : new ClassA();
		//无论obj最终是那个实现类，下面这句都能够正确调用println方法,待输出的参数
		getPrintlnMH(obj).invokeExact("anla7856");
	}
}
