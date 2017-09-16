package anla.VM.byteEngine;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
/**
 * 本打算直接执行爷爷类的方法，但貌似没有成功。
 * @author anla7856
 *
 */
public class Son {
	class GrandFather {
		void thinking() {
			System.out.println("i am grandfather");
		}
	}

	class Father extends GrandFather {
		void thinking() {
			System.out.println("i am father");
		}
	}

	class MySon extends Father {
		void thinking() {
			try {
				MethodType mt = MethodType.methodType(void.class);
				MethodHandle mh = MethodHandles.lookup().findSpecial(
						GrandFather.class, "thinking", mt, getClass());
				mh.invoke(this);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		(new Son().new MySon()).thinking();
	}
}
