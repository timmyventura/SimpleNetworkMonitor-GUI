package monitor.network;


import java.util.ArrayList;
import java.util.List;
import org.jnetpcap.PcapIf;

import monitor.network.utils.LinuxNetworkUtils;


public class LinuxNetworkFactory extends OSNetworkFactory {
	
	private String defaultGateway;
	private List<String> dnsServers = new ArrayList<>();
	
	public LinuxNetworkFactory(PcapIf device) {
		
		setDevice(device);
		
	}
	
	@Override
	public String getDefaultGateway() {
		
		if(defaultGateway==null) {
			defaultGateway = LinuxNetworkUtils.getGateway();
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
			dnsServers = LinuxNetworkUtils.getDnsServers();
			return dnsServers.toArray(new String [dnsServers.size()]);
		}
		else
          return dnsServers.toArray(new String [dnsServers.size()]);
	}

	@Override
	public void setNameServer(String dns) {
		
		dnsServers.add(dns);

	}


}
