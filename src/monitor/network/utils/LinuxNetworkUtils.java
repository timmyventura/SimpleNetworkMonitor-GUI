package monitor.network.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;

public class LinuxNetworkUtils {

	private  String routingTableCommand;	
	private  String resolverPath;
	private  String gatewayPattern;
	private  String dnsPattern;
	
	
	private LinuxNetworkUtils() {}
	
	 public String getGateway() {
	    	
		 String [] gateway = new String[1];
		 
	    	try {
	    		
				Process netst_proc = Runtime.getRuntime().exec(getRoutingTableCommand());
				netst_proc.waitFor();
				
				List<String> lines = readFromInputStream(netst_proc.getInputStream(), null);
				lines.forEach((line) -> {
					
				   try {
					   
					String [] fields = line.split("\\s+");
					if(fields[0].equals(getGatewayPattern())) gateway[0] = fields[1];
					
				   }catch(ArrayIndexOutOfBoundsException e) {
				        	
			        	Logging.log(LinuxNetworkUtils.class, MessageType.ERROR, e);
			        							
					}
				});
			
		    
	    	}catch(NullPointerException e) {
			
	    		Logging.log(Devices.class, MessageType.INFO, e);
			
	    	}catch (IOException e) {
				
				Logging.log(Devices.class, MessageType.ERROR, e);
		       	
			}catch (InterruptedException e) {

				Logging.log(LinuxNetworkUtils.class, MessageType.ERROR, e);
		       	
		       	Logging.viewLogMessage(e, MessageType.ERROR);
			}
	
	    	return gateway[0];
	    	
	    	
	    }
	 
	    
	public List<String> getDnsServers() {
	    	
		  List<String> dnsServers = new ArrayList<>();
		  
	    	try {

				List<String> lines = Files.readAllLines(Paths.get(getResolverPath()));
				lines.forEach((line) -> {
					String [] fields = line.split("\\s+");
					if(fields[0].equals(getDnsPattern())) 
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

	public String getRoutingTableCommand() {
		return routingTableCommand;
	}

	public void setRoutingTableCommand(String routingTable) {
		this.routingTableCommand = routingTable;
	}

	public String getResolverPath() {
		return resolverPath;
	}

	public void setResolverPath(String resolvePath) {
		this.resolverPath = resolvePath;
	}

	public String getGatewayPattern() {
		return gatewayPattern;
	}

	public void setGatewayPattern(String gatewayPattern) {
		this.gatewayPattern = gatewayPattern;
	}

	public String getDnsPattern() {
		return dnsPattern;
	}

	public void setDnsPattern(String dnsPattern) {
		this.dnsPattern = dnsPattern;
	}

	
}
