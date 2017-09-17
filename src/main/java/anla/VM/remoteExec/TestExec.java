package anla.VM.remoteExec;

import java.io.FileInputStream;
import java.io.InputStream;

public class TestExec {
	public static void main(String[] args) throws Exception{
		InputStream is = new FileInputStream("/home/anla7856/桌面/Test.class");
		byte[] b = new byte[is.available()];
		is.read(b);
		is.close();
		
		System.out.println(JavaClassExecuter.execute(b));
	}
}
