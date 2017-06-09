package monitor.network;

import org.jnetpcap.PcapIf;

public interface NetworkFactory {

	public String getDefaultGateway();
	public void setDefaultGateway(String defaultGateway);
	public String [] getNameServers();
	public void setNameServer(String dns);
	public String getExternalIP();
	public void setExternalIP(String externalip);
	public String getHostname();
	public void setHostname(String hostname);
	public String getLocalIP();
	public void setLocalIP(String localip);
	public String getNetworkMask();
	public void setNetworkMask(String netmask);
	public PcapIf getDevice();
	public void setDevice(PcapIf device);

	
}
