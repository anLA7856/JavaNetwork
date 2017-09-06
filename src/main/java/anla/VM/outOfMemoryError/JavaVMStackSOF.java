package anla.VM.outOfMemoryError;

/**
 * vm Args:-Xss=128k
 * @author anla7856
 * -verbose:gc -Xss20K
 * 结果就是，java挂了
 * 单线程下虚拟机栈溢出
 */
public class JavaVMStackSOF {
	private int stackLength = 1;
	
	public void stackLeak(){
		this.stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length:" + oom.stackLength);
			throw e;
		}
	}
	
	
}
