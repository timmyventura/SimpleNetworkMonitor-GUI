package monitor.protocols;

import java.util.HashMap;
import java.util.Map;

public class UDPService {

private static final Map<Integer, String> map = new HashMap<>();
	

	private static final Integer SMTP = 25;
	private static final Integer IMAP = 143;
	private static final Integer VIBER_52 = 5243;
	private static final Integer VIBER_97 = 9785;
	public static final Integer SSH = 22;
	public static final Integer TELNET = 23;
	public static final Integer DHCP_SERVER = 67;
	public static final Integer DHCP_CLIENT = 68;
	public static final Integer NTP = 123;
	public static final Integer SNMP_AGENT = 161;
	public static final Integer SNMP_TRAPS = 162;
	public static final Integer DNS = 53;
	public static final Integer NBNS = 137;

	private static final Integer NULL = 0;
	
    
	private static final String post = "Post";
	private static final String ssh = "SSH";
	private static final String telnet = "Telnet";
	private static final String dhcp = "DHCP";
	private static final String ntp = "NTP";
	private static final String snmp = "SNMP";
	private static final String dns = "DNS";
	private static final String nbns = "NetBios Name Service";
	private static final String viber = "Viber";
	
	
    private static final String null_service = "Unknown UDP";
	
	
	static {
		

	//////Post-Services////////
		map.put(SMTP, post);
		map.put(IMAP, post);
	//////Messengers//////////
		map.put(VIBER_52, viber);
		map.put(VIBER_97, viber);
    //////SSH, TELNET/////////
		map.put(SSH, ssh);
		map.put(TELNET, telnet);
	//////DNS, DHCP/////////
	    map.put(DNS, dns);
		map.put(DHCP_CLIENT, dhcp);
		map.put(DHCP_SERVER, dhcp);
	//////NTP, SNMP/////////
		map.put(NTP, ntp);
		map.put(SNMP_TRAPS, snmp);
		map.put(SNMP_AGENT, snmp);
	//////NBNS///////////////
		map.put(NBNS, nbns);
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
