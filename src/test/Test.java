package test;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jnetpcap.packet.JHeader;
import org.jnetpcap.protocol.network.Ip4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import monitor.controller.FrameModelController;
import monitor.model.InformMediator;
import monitor.network.utils.ExternalAddress;
import monitor.network.utils.ExternalAddress.HTTPRequestParameters;
import monitor.network.utils.LinuxNetworkUtils;
import monitor.network.utils.WindowsNetworkUtils;
import monitor.protocols.EthernetService;
import monitor.protocols.IPService;
import monitor.protocols.ProtocolSet;
import monitor.protocols.TCPService;
import monitor.protocols.UDPService;

public class Test {

	public static final Short IPv4 = 0x0800;
	public static final Short ARP = 0x0806;
	public static final Short WAKEonLAN = 0x0842;
	public static final Short DECnet = 0x6003;
	public static final Short RARP = (short)0x8035;
	public static final Short AppleTalk = (short)0x809B;
	public static final Short IEEE8021Q = (short)0x8100;
	public static final Short IPX = (short)0x8137;
	public static final Short ETHFLOWCON = (short)0x8808;
	public static final Short IPv6 = (short)0x86DD;
	public static final Short CobraNet = (short)0x8819;
	public static final Short MPLSunicast = (short)0x8847;
	public static final Short MPLSmulticast = (short)0x8848;
	public static final Short PPPoEDiscovery = (short)0x8863;
	public static final Short PPPoESession = (short)0x8864;
	public static final Short IEEE8021X = (short)0x888E;
	public static final Short LLDP = (short)0x88CC;
	public static final Short TEST = (short)0x9000;
	
	
	enum AlarmPoints{
		  
		KITCHEN, BATHROOM, LIVING;
		 
	}
	
	interface Command {
       
		void action();
		
	}
	
	public static final Map<AlarmPoints, Command> testmap = new HashMap<Test.AlarmPoints, Test.Command>();
	
	static {
		
		testmap.put(AlarmPoints.KITCHEN, () -> System.out.println("I'm at a kitchen"));
		
		testmap.put(AlarmPoints.BATHROOM, () -> System.out.println("I'm at a bathroom"));
		
		testmap.put(AlarmPoints.LIVING, () -> System.out.println("I'm at a living room"));
		
	}
	
	public static void main(String [] args) throws SocketException {
		/*
		ApplicationContext context = new GenericXmlApplicationContext("classpath:monitor/net-service-context.xml");
		TCPService tcpService = (TCPService)context.getBean(TCPService.class);
		UDPService udpService = (UDPService)context.getBean(UDPService.class);
		IPService ipService = (IPService)context.getBean(IPService.class);
		EthernetService etherService = (EthernetService)context.getBean(EthernetService.class);
		System.out.println(tcpService.getService(80, 1231));
		System.out.println(udpService.getService(53, 1231));
		System.out.println(ipService.getService(56));
		System.out.println(etherService.getService((short)0x888E));
		*/
		
		//System.out.println(tcpService.getPortNameMap());
       
		/*
		Field [] fields = Test.class.getFields();
		
		for (Field field : fields) {
			
			try {
				System.out.printf(("<entry key=\"%s\" value=\"%s\"/>\n"), field.get(null), field.getName().toUpperCase());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		*/
		/*
		ApplicationContext context = new GenericXmlApplicationContext("classpath:monitor/test-context.xml");
		FrameModelController fmc = (FrameModelController)context.getBean(FrameModelController.class);
		//System.out.printf("Resolf path - %5s, RoutingTable - %5s, GatewayPattern -%5s, DnsPattern - %5s", fmc.getNameServers(), fmc.getRoutingTable(), fmc.getGatewayPattern(), fmc.getIpPattern());		
		fmc.runningInitialFrame();
		//System.out.println(fmc.getNetworkFactory());
		*/
		//testmap.get(AlarmPoints.BATHROOM).action("I'm in a bathroom. Yap");

		/*
		testmap.get(AlarmPoints.BATHROOM).action();
		
		IPAddress ip = new IPAddress();
		ip.setIpAddress("77.222.53.0");
		//ip.setMask("255.255.255.0"); 
		System.out.println(ip.getNetworkAddress());
		*/
		
		JHeader head = ProtocolSet.IPv4.getInstance();
		Ip4 ip4 = new Ip4();
		System.out.println(ip4.getDescription());
		
		
	}
	
	
}



class IPAddress{
	
	private String ipAddress;
	private String mask;
	private static final Map<String, String> map = new HashMap<>();
	private static final Map<String, String> prefix = new HashMap<>();
	
	
	static {

		map.put("77.222.40.0", "255.255.254.0");
		map.put("77.222.42.0", "255.255.254.0");
		map.put("77.222.44.0", "255.255.252.0");
		map.put("77.222.50.0", "255.255.254.0");
		map.put("77.222.52.0", "255.255.254.0");
		map.put("77.222.54.0", "255.255.254.0");
		prefix.put("255.255.254.0", "/23");
		prefix.put("255.255.252.0", "/22");
	}
	
	public IPAddress() {
		
	}
	
	public IPAddress(String ipAddress, String mask) {
		
		this.ipAddress=ipAddress;
		this.mask=mask;
		
	}
	
	
	public String display(Integer [] address) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<address.length-1; i++) {
			
			sb.append(address[i]).append(".");
			
		}
		sb.append(address[address.length-1]);
		
		return sb.toString();
		
	}
	
	public String getNetworkAddress() {
				
		
		StringBuilder net = new StringBuilder();
		
		 String [] ua = ipAddress.split("\\.");
		 
		 if((Integer.parseInt(ua[0])>255 || Integer.parseInt(ua[1])>255 ||Integer.parseInt(ua[2])>255 || Integer.parseInt(ua[3])>255)) {
			 
			 System.err.println("Wrong address format, octets should not exceed 255 ");
		     System.exit(0);
			 
		 }
		
		 map.forEach((addr, mask) ->{
		
			 String [] m = mask.split("\\.");
			 String [] a = addr.split("\\.");
			
			 
			 
			 
			 if( m.length <4 || a.length<4 ||ua.length<4) {
				 System.err.println("Wrong address format, need X.X.X.X");
			     System.exit(0);
			 }
			 else {
		
			 try {
				 
			 int [] intAddr = { Integer.parseInt(ua[0])&Integer.parseInt(m[0]), 
					            Integer.parseInt(ua[1])&Integer.parseInt(m[1]),
					            Integer.parseInt(ua[2])&Integer.parseInt(m[2]),
					            Integer.parseInt(ua[3])&Integer.parseInt(m[3]),};
			 
			 int [] netAddr = {Integer.parseInt(a[0]),
					           Integer.parseInt(a[1]),
					           Integer.parseInt(a[2]),
					           Integer.parseInt(a[3])};
			 if(equals(netAddr, intAddr)) {
				 
					net.append(addr).append(prefix.get(mask));
				 }
			 
			 }catch( NumberFormatException e) {
				 
				 System.err.println("Wrong address format, need all decimal and max is 255.255.255.255");
				 System.exit(0);
			 }
			 }
			 
			 
						
		});
		if(net.length()==0)
		  return "No network";
		else
	      return net.toString();
		
	}
	
	
	public boolean equals(int [] addressInMap, int [] userAddress) {
		
		return ((addressInMap[0]==userAddress[0])
			   &&
			   (addressInMap[1]==userAddress[1])
			   &&
			   (addressInMap[2]==userAddress[2])
			   &&
			   (addressInMap[3]==userAddress[3]));
			   
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}
	
}

