package anla.VM.optimize;

import java.util.List;

/**
 * java泛型中，list<String>和list<Integer>是一样的
 * @author anla7856
 *
 */
public class GenericTypes {
	public static String method(List<String> list){
		System.out.println("invoke method(List<String> list)");
		return "";
	}
	
//	public static int method(List<Integer> list){
//		System.out.println("invoke method(List<Integer> list)");
//		return 1;
//	}
}
