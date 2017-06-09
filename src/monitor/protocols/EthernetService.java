package monitor.protocols;

import java.util.HashMap;
import java.util.Map;

public class EthernetService {

	
    private static final Map<Short, String> map = new HashMap<>();
	
	private static final Short IPv4 = 0x0800;
	private static final Short ARP = 0x0806;
	private static final Short WAKEonLAN = 0x0842;
	private static final Short DECnet = 0x6003;
	private static final Short RARP = (short)0x8035;
	private static final Short AppleTalk = (short)0x809B;
	private static final Short IEEE8021Q = (short)0x8100;
	private static final Short IPX = (short)0x8137;
	private static final Short ETHFLOWCON = (short)0x8808;
	private static final Short IPv6 = (short)0x86DD;
	private static final Short CobraNet = (short)0x8819;
	private static final Short MPLSunicast = (short)0x8847;
	private static final Short MPLSmulticast = (short)0x8848;
	private static final Short PPPoEDiscovery = (short)0x8863;
	private static final Short PPPoESession = (short)0x8864;
	private static final Short IEEE8021X = (short)0x888E;
	private static final Short LLDP = (short)0x88CC;
	private static final Short TEST = (short)0x9000;
	private static final Short NULL = 0x0000;

	private static final String arp = "ARP";
	private static final String ipv4 = "IPv4";
	private static final String ipv6 = "IPv6";
    private static final String wake = "Wake On Lan";
	private static final String dec = "DEC Net";
    private static final String rarp = "Reverse ARP";
	private static final String ipx = "IPX";
    private static final String tag = "IEEE 802.1Q";
	private static final String test = "Cisco Loopback";
    private static final String lldp = "LLDP";
	private static final String secure = "IEEE 802.1x";
    private static final String cobra = "Cobra Net";
    private static final String pppoe = "PPPoE";
	private static final String mpls = "MPLS";
	private static final String apple = "Apple Talk";
	private static final String flow = "Flow Control";
    private static final String null_serv = "Unknown EtherType";
   
    
	
	static {
		
		map.put(ARP, arp);
		map.put(NULL, null_serv);
		map.put(IPv4, ipv4);
		map.put(WAKEonLAN, wake);
		map.put(DECnet, dec);
		map.put(RARP, rarp);
		map.put(AppleTalk, apple);
		map.put(IEEE8021Q, tag);
		map.put(IPX, ipx);
		map.put(ETHFLOWCON, flow);
		map.put(IPv6, ipv6);
		map.put(CobraNet, cobra);
		map.put(MPLSunicast, mpls);
		map.put(MPLSmulticast, mpls);
		map.put(PPPoEDiscovery, pppoe);
		map.put(PPPoESession, pppoe);		
		map.put(IEEE8021X, secure);
		map.put(LLDP, lldp);
		map.put(TEST, test);

	}
	
	
	public static String getService(Short port) {
	
		
		return map.getOrDefault(port, map.get(NULL));		
		
	}
	
}
