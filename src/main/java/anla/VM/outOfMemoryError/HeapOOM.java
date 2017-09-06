package anla.VM.outOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:HeapDumpOnOutOfMemoryError
 * detail config of vm:
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @author anla7856
 * java堆溢出
 *
 */
public class HeapOOM {
	static class OOMObject{
		
	}
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<HeapOOM.OOMObject>();
		while(true){
			list.add(new OOMObject());
		}
	}
}
