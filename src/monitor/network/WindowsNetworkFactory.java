package monitor.network;

import java.util.ArrayList;
import java.util.List;

import org.jnetpcap.PcapIf;

import monitor.network.utils.WindowsNetworkUtils;

public class WindowsNetworkFactory extends OSNetworkFactory {

	private String defaultGateway;
	private List<String> dnsServers = new ArrayList<>();
	private WindowsNetworkUtils windowsNetworkUtils;
	
	public WindowsNetworkFactory(PcapIf device) {
		
		setDevice(device);
		
	}
	
	public WindowsNetworkFactory() {
		
		
	}
	
	@Override
	public String getDefaultGateway() {
		
		if(defaultGateway==null) {
			defaultGateway = getWindowsNetworkUtils().getGateway();
			return defaultGateway;
		}
		else
		  return defaultGateway;
	}

	@Override
	public void setDefaultGateway(String defaultGateway) {
		
		this.defaultGateway = defaultGateway;

	}

	@Override
	public String[] getNameServers() {
		
		
		if(dnsServers.size()==0) {
			dnsServers = getWindowsNetworkUtils().getDnsServers();
			return dnsServers.toArray(new String [dnsServers.size()]);
		}
		else
         return dnsServers.toArray(new String [dnsServers.size()]);
	}

	@Override
	public void setNameServer(String dns) {
		
		dnsServers.add(dns);

	}

	public WindowsNetworkUtils getWindowsNetworkUtils() {
		return windowsNetworkUtils;
	}

	public void setWindowsNetworkUtils(WindowsNetworkUtils windowsNetworkUtils) {
		this.windowsNetworkUtils = windowsNetworkUtils;
	}

}
