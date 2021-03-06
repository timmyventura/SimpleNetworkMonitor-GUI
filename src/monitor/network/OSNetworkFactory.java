package monitor.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jnetpcap.PcapIf;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;
import monitor.network.utils.ExternalAddress;

public abstract class OSNetworkFactory implements NetworkFactory{

	
	private String externalIp;
	private String hostname;
	private String localip;
	private String netmask;
	private PcapIf device;
	private String ipPattern;
	private ExternalAddress externalAddress;
	
	public abstract String getDefaultGateway();
	public abstract void setDefaultGateway(String defaultGateway);
	public abstract String [] getNameServers();
	public abstract void setNameServer(String dns);
	
	public  String getExternalIP() {
		
		if(externalIp==null) {
			externalIp = externalAddress.getExternalIP();
			return externalIp;
		}
		else
		  return externalIp;
		  	}
	
	public  void setExternalIP(String externalip) {
		
		 this.externalIp = externalip;
	}
	
	public String getHostname() {
		
		if(hostname==null) {
			try {
				hostname = InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {

		       	Logging.log(this.getClass(), MessageType.ERROR, e);
		       	
		       	Logging.viewLogMessage(e, MessageType.ERROR);
			}
			return hostname;
		}
		else
		   return hostname;
		
	}
	
	public void setHostname(String hostname) {
		
		this.hostname = hostname;
		
	}
	
	public  String getLocalIP() {
         if(localip==null) {
			
			Pattern p = Pattern.compile(getIpPattern());
			device.getAddresses().forEach(addr -> {
				
			   	Matcher m = p.matcher(addr.getAddr().toString());
			   	if(m.find()) localip = m.group();
			    	
			});

		return localip;
		}
		else
			return localip;
	}
	
	public void setLocalIP(String localip) {
		
		this.localip = localip;
		
	}
	
	public  String getNetworkMask() {
		
         if(netmask==null) {
			
			Pattern p = Pattern.compile(getIpPattern());
			device.getAddresses().forEach(addr -> {
				try {
			   	Matcher m = p.matcher(addr.getNetmask().toString());
			   	if(m.find()) netmask = m.group();
				}
				catch(NullPointerException e) {	
					
			       	Logging.log(this.getClass(), MessageType.INFO, e);  	
					
				} 	    	
			});
		return netmask;
		}
		else
			return netmask;
	}
	
	public  void setNetworkMask(String netmask) {
		
		this.netmask = netmask;
		
	}
	
	public void setDevice(PcapIf device) {
		
		this.device = device;
	}
	
	public PcapIf getDevice() {
		
		return device;
	}
	
	public String getIpPattern() {
		return ipPattern;
	}

	public void setIpPattern(String ipPattern) {
		this.ipPattern = ipPattern;
	}
	public ExternalAddress getExternalAddress() {
		return externalAddress;
	}
	public void setExternalAddress(ExternalAddress externalAddress) {
		this.externalAddress = externalAddress;
	}
	
	
}
