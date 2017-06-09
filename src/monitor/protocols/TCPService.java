package monitor.protocols;

import java.util.HashMap;
import java.util.Map;

public class TCPService {

	
	private static final Map<Integer, String> map = new HashMap<>();
	
	public static final Integer HTTP = 80;
	public static final Integer HTTPS = 443;
	public static final Integer FTP = 21;
	public static final Integer FTP_DATA = 20;
	public static final Integer SSH = 22;
	public static final Integer TELNET = 23;
	public static final Integer SMTP = 25;
	public static final Integer IMAP = 143;
	public static final Integer IMAPS = 993;
	public static final Integer SMTPS = 465;
	private static final Integer VIBER_52 = 5242;
	private static final Integer VIBER_42 = 4244;
	
	
	
	public static final Integer OTHER = 0;
    
	private static final String web = "Web";
	private static final String post = "Post";
	private static final String ssh = "SSH";
	private static final String telnet = "Telnet";
	private static final String ftp = "FTP";
	private static final String viber = "Viber";
	
    private static final String null_service = "Unknown TCP";
	
	
	static {
		
	///////Web-Services////////
		map.put(HTTP, web);
		map.put(HTTPS, web);
	//////Post-Services////////
		map.put(SMTP, post);
		map.put(SMTPS, post);
		map.put(IMAP, post);
		map.put(IMAPS, post);
	//////FTP-Service//////////
		map.put(FTP, ftp);
		map.put(FTP_DATA, ftp);
	//////Messengers//////////
		map.put(VIBER_52, viber);
		map.put(VIBER_42, viber);	
	/////SSH-Service//////////	
	    map.put(SSH, ssh);
	/////Telnet-Serivce///////
	    map.put(TELNET, telnet);
	/////Other-Service////////
	    map.put(OTHER, null_service);
	}
	
	
	public static String getService(int src_port, int dst_port) {
		
		if(map.containsKey(src_port))
			return map.get(src_port);
		else
			if(map.containsKey(dst_port))
			  return map.get(dst_port);
			else
				return map.get(OTHER);
	}
	
}
