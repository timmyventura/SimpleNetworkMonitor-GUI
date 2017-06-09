package monitor.protocols;

import java.util.HashMap;
import java.util.Map;

public class ICMPService {

private static final Map<Integer, String> map = new HashMap<>();
	
	private static final Integer ICMP = 1;
	private static final Integer NULL = 0;

	private static final String icmp = "ICMP";
    private static final String null_serv = "ICMP";
	
	
	static {
		
	///////ARP-Services////////
		map.put(ICMP, icmp);
		map.put(NULL, null_serv);
	}
	
	
	public static String getService(Integer port) {
		
		if(map.containsKey(port))
			return map.get(port);
		else
			return map.get(NULL);
	}
	
}
