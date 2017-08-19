package anla.Network.internetAddress;

import java.net.InetAddress;

/**
 * 判断是否是垃圾邮件地址。
 * @author anla7856
 *
 */
public class SpamCheck {
		
	public static final String BLACKHOLE = "sbl.spamhaus.org";
	
	private static boolean isSpammer(String arg){
		try {
			InetAddress address = InetAddress.getByName(arg);
			byte[] quad = address.getAddress();
			String query = BLACKHOLE;
			for(byte octet : quad){
				int unsignedByte = octet < 0? octet+256:octet;
				query = unsignedByte+"."+query;
			}
			InetAddress.getByName(query);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
		String[] strs = {"207.34.56.23","125.12.32.4","130.130.130.130"};
		for(String arg:strs){
			if(isSpammer(arg)){
				System.out.println(arg+"is a known spammer");
			}else{
				System.out.println(arg+"appears legitimate");
			}
			
		}
	}
	
}
