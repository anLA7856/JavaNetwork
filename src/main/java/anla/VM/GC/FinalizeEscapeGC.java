package anla.VM.GC;
/**
 * 1、对象在gc时，可以自我拯救
 * 2、一个对象的finalize方法只能被系统调用一次。
 * @author anla7856
 *
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive(){
		System.out.println("yes, I am still alive");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize method executed!");
		//来个自救
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Exception{
		SAVE_HOOK = new FinalizeEscapeGC();
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,I am dead");
		}
		
		//下面这段代码与上面完全相同，但是这次自救却失败了。
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no,I am dead");
		}
		
	}
}
