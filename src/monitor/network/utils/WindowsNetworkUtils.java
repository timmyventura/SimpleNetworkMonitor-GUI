package monitor.network.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;

public class WindowsNetworkUtils {

	
	private String routingTableCommand;	
	private String nameServersCommand;
	private String gatewayPattern;
	private String ipPattern;

	
	private WindowsNetworkUtils() {}
	
	 public String getGateway() {
	    	
		 String [] gateway = new String[1];
		 
	    	try {
	    		
				Process netst_proc = Runtime.getRuntime().exec(getRoutingTableCommand());
				
				netst_proc.waitFor();
				
				List<String> lines = readFromInputStream(netst_proc.getInputStream());
				
				lines.forEach((line) -> {
					
					try {
					
					String [] fields = line.split("\\s+");
					
					if(fields[3].equals(getGatewayPattern())) gateway[0] = fields[5];
					
					}catch(ArrayIndexOutOfBoundsException e) {
					  									        	
			        	Logging.log(WindowsNetworkUtils.class, MessageType.ERROR, e);
			        							
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
	    	
		  List<String> lines = null;
		  			
				try {
					
					Process netst_proc = Runtime.getRuntime().exec(getNameServersCommand());
					
					netst_proc.waitFor();
					
					lines = readFromInputStream(netst_proc.getInputStream(), getIpPattern());

				} catch (IOException e) {
					
					Logging.log(WindowsNetworkUtils.class, MessageType.ERROR, e);
					
				} catch (InterruptedException e) {

			       	Logging.log(WindowsNetworkUtils.class, MessageType.ERROR, e);
			       	
			       	Logging.viewLogMessage(e, MessageType.ERROR);
					
				}
				
			return lines;
				
	    	
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

	public String getNameServersCommand() {
		return nameServersCommand;
	}

	public void setNameServersCommand(String nameServers) {
		this.nameServersCommand = nameServers;
	}

	public String getGatewayPattern() {
		return gatewayPattern;
	}

	public void setGatewayPattern(String gatewayPattern) {
		this.gatewayPattern = gatewayPattern;
	}

	public String getIpPattern() {
		return ipPattern;
	}

	public void setIpPattern(String ipPattern) {
		this.ipPattern = ipPattern;
	}
	
	
	
}
