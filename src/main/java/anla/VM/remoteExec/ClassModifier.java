package anla.VM.remoteExec;

/**
 * 修改Class文件，暂时只提供修改常量池的功能
 * 
 * @author anla7856
 *
 */
public class ClassModifier {
	/**
	 * class文件中常量池的起始偏移
	 */
	private static final int CONSTANT_POOL_COUNT_INDEX = 9;

	/**
	 * CONSTANT_utf8_info常量的tag标志
	 */
	private static final int CONSTANT_UTF8_INFO = 1;

	/**
	 * 常量池中11种常量所占的长度，constant_utf8_info除外，因为它不是定长的。
	 */
	private static final int[] CONSTANT_ITEM_LENGTH = { -1, -1, -1, 5, 5, 9, 9,
			3, 3, 5, 5, 5, 5 };

	private static final int u1 = 1;
	private static final int u2 = 2;

	private byte[] classByte;

	public ClassModifier(byte[] classByte) {
		this.classByte = classByte;
	}

	public int getConstantPoolCount() {
		return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
	}

	/**
	 * 仅仅是替换class文件中常量池的信息。
	 * @param oldStr
	 * @param newStr
	 * @return
	 */
	public byte[] modifyUTF8Constant(String oldStr, String newStr) {
		int cpc = getConstantPoolCount();
		int offset = CONSTANT_POOL_COUNT_INDEX + u2;
		for (int i = 0; i < cpc; i++) {
			int tag = ByteUtils.bytes2Int(classByte, offset, u1);
			if (tag == CONSTANT_UTF8_INFO) {
				int len = ByteUtils.bytes2Int(classByte, offset + u1, u2);
				offset += (u1 + u2);
				String str = ByteUtils.bytes2String(classByte, offset, len);
				if (str.equalsIgnoreCase(oldStr)) {
					byte[] strBytes = ByteUtils.string2Bytes(newStr);
					byte[] strLen = ByteUtils.int2Bytes(newStr.length(), u2);
					classByte = ByteUtils.bytesReplace(classByte, offset - u2,
							u2, strLen);
					classByte = ByteUtils.bytesReplace(classByte, offset, len,
							strBytes);
					return classByte;
				} else {
					offset += len;
				}
			} else {
				offset += CONSTANT_ITEM_LENGTH[tag];
			}
		}
		return classByte;
	}
}
