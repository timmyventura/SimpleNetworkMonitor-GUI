package monitor.protocols;

import java.util.HashMap;
import java.util.Map;

public class IPService {

    private static final Map<Integer, String> map = new HashMap<>();
	
	private static final Integer ESP_IPSec = 50;
	private static final Integer GRE = 47;
	private static final Integer ICMP = 1;
	private static final Integer IGMP = 88;
	private static final Integer TCP = 6;
	private static final Integer UDP = 17;
	private static final Integer IPv4_Over_IP = 45;
	private static final Integer IPv6_ICMP = 58;
	private static final Integer VRRP = 112;
	
	
	private static final Integer NULL = 0;

	private static final String gre = "Tunnel";
	private static final String icmp = "ICMP";
	private static final String igmp = "Multicast";
	private static final String ipsec = "Secure";
	private static final String tcp = "TCP";
    private static final String udp = "UDP";
    private static final String service = "Service Traffic";
    
    
    private static final String null_serv = "Unknown IP";
	
	
	static {
		
	///////Secure-Services////////
		map.put(ESP_IPSec, ipsec);
		map.put(GRE, gre);
	//////Multicast-Services////////
		map.put(IGMP, igmp);
	//////ICMP-Service//////////
		map.put(ICMP, icmp);
	//////Service-traffic///////
		map.put(VRRP, service);
	/////Other-Services/////////
		map.put(NULL, null_serv);
		
	}
	
	
	public static String getService(Integer port) {
		
		
		if(map.containsKey(port))
			return map.get(port);
		else
		{   
			System.out.println(port);
			return map.get(NULL);
		}
	}
	
	
	
}
