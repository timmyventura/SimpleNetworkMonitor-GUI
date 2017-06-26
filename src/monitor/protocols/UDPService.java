package monitor.protocols;

import java.util.HashMap;
import java.util.Map;

public class UDPService {

private static final Map<Integer, String> map = new HashMap<>();
	

	private static final Integer SMTP = 25;
	private static final Integer IMAP = 143;
	private static final Integer VIBER_52 = 5243;
	private static final Integer VIBER_97 = 9785;
	
	
	private static final Integer NULL = 0;
	
    
	private static final String web = "Web";
	private static final String post = "Post";
	private static final String ssh = "SSH";
	private static final String telnet = "Telnet";
	private static final String ftp = "FTP";
	private static final String viber = "Viber";
	
	
	
    private static final String null_service = "Unknown UDP";
	
	
	static {
		

	//////Post-Services////////
		map.put(SMTP, post);
		map.put(IMAP, post);
	//////Messengers//////////
		map.put(VIBER_52, viber);
		map.put(VIBER_97, viber);
	/////Other-Service////////
	    map.put(NULL, null_service);
	}
	
	
	public static String getService(Integer port) {
		
		if(map.containsKey(port))
			return map.get(port);
		else
			return map.get(NULL);
	}
	
   public static String getService(int src_port, int dst_port) {
		
		if(map.containsKey(src_port))
			return map.get(src_port);
		else
			if(map.containsKey(dst_port))
			 return map.get(dst_port);
			else
				return map.get(NULL);
	}
	
}
