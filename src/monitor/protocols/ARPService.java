package monitor.protocols;

import java.util.HashMap;
import java.util.Map;

public class ARPService {
	
	private static final Map<Integer, String> map = new HashMap<>();
	
	private static final Integer ARP = 806;
	private static final Integer NULL = 0;

	private static final String arp = "ARP";
    private static final String null_serv = "ARP";
	
	
	static {
		
	///////ARP-Services////////
		map.put(ARP, arp);
		map.put(NULL, null_serv);
	}
	
	
	public static String getService(Integer port) {
		
		if(map.containsKey(port))
			return map.get(port);
		else
			return map.get(NULL);
	}
	
	
}
