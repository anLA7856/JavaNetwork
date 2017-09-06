package anla.VM.outOfMemoryError;
/**
 * VM Args: -Xss2M
 * @author anla7856
 * 多线程下，虚拟机栈溢出。
 */
public class JavaVMStackOOM {
	private void dontStop(){
		while(true){
			
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
