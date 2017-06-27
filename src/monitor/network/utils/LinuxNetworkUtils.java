package monitor.network.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;

public class LinuxNetworkUtils {

	private static final String netstat_rn = "netstat -rn";	
	private static final String netstat_p = "netstat -p";
	private static final String resolve_path = "/etc/resolv.conf";
	private static final String gateway_pattern = "0.0.0.0";
	private static final String dns_pattern = "nameserver";
	
	
	private LinuxNetworkUtils() {}
	
	 public static String getGateway() {
	    	
		 String [] gateway = new String[1];
		 
	    	try {
	    		
				Process netst_proc = Runtime.getRuntime().exec(netstat_rn);
				netst_proc.waitFor();
				
				List<String> lines = readFromInputStream(netst_proc.getInputStream(), null);
				lines.forEach((line) -> {
					
					String [] fields = line.split("\\s+");
					if(fields[0].equals(gateway_pattern)) gateway[0] = fields[1];
					
				});
		    
	    	}catch(NullPointerException e) {
			
	    		Logging.log(Devices.class, MessageType.INFO, e.getMessage());
			
	    	}catch (IOException e) {
				
				Logging.log(Devices.class, MessageType.ERROR, e.getMessage());
		       	
			}catch (InterruptedException e) {

				Logging.log(LinuxNetworkUtils.class, MessageType.ERROR, e);
		       	
		       	Logging.viewLogMessage(e, MessageType.ERROR);
			}
	    	return gateway[0];
	    	
	    }
	 
	  public static Map<String, String> getApplicationPorts(String pattern){
		  
        Map<String, String> application = new HashMap<>();		 
        
	    	try {
	    		
				Process netst_proc = Runtime.getRuntime().exec(netstat_p);
				List<String> lines = readFromInputStream(netst_proc.getInputStream(), pattern);
				lines.forEach((line) -> {
					try {
					String [] fields = line.split("\\s+");
		
					application.put(fields[3].split(":")[1], fields[6].split("/")[1]);
					}catch(ArrayIndexOutOfBoundsException e) {
						
						
						Logging.log(LinuxNetworkUtils.class, MessageType.INFO, e);
						
					}
				});
				

	    	}catch(NullPointerException e) {
				
	    		Logging.log(Devices.class, MessageType.INFO, e.getMessage());
			
	    	}catch (IOException e) {
				
				Logging.log(Devices.class, MessageType.ERROR, e.getMessage());
		       	
			}
	    	return application;
		  
	  }
	    
	  public static List<String> getDnsServers() {
	    	
		  List<String> dnsServers = new ArrayList<>();
		  
	    	try {

				List<String> lines = Files.readAllLines(Paths.get(resolve_path));
				lines.forEach((line) -> {
					String [] fields = line.split("\\s+");
					if(fields[0].equals(dns_pattern)) 
					dnsServers.add(fields[1]);
				});
				

			} catch (IOException | NullPointerException e) {
				
				Logging.log(LinuxNetworkUtils.class, MessageType.ERROR, e);
				
			} 
	    	return dnsServers;
	    	
	    }
	    
	  public static List<String> readFromInputStream(InputStream input) throws IOException{
	    	
	    	return readFromInputStream(input, null);
	    	
	    }
	  
	  public static List<String> readFromInputStream(InputStream input, String pattern) throws IOException{
	    	
	    	List<String> buffer = new ArrayList<>();
	    	try(BufferedReader br = new BufferedReader(new InputStreamReader(input))){
	    	String temp;
	    	if(pattern!=null) {
	    		
	    		Pattern p = Pattern.compile(pattern);
	    	    while((temp=br.readLine())!=null) {
	    		  Matcher m = p.matcher(temp);
	    		   while(m.find()) buffer.add(m.group());
	    		
		    	}
	    	}
	    	else
	    	{
	    		while((temp=br.readLine())!=null) {
		    		buffer.add(temp);
	    	}
	    	}
	    	}
	    	
	    	return buffer;
	    	
	    }
	
}
