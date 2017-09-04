package monitor.protocols;

import java.util.Map;

public class TCPService implements NetworkService{

	
	private Map<Integer, String> portNameMap;
	
	public static final Integer NULL = 0;
	
	
	public String getService(int src_port, int dst_port) {
		
				
		if(portNameMap.containsKey(src_port))
			return portNameMap.get(src_port);
		else
			if(portNameMap.containsKey(dst_port))
			  return portNameMap.get(dst_port);
			else
				return portNameMap.get(NULL);
	}
	

	@Override
	public String getService(int port) {
		
		throw new UnsupportedOperationException(" Usinig getService(int src_port, int dst_port) method");
	}


	@Override
	public String getService(short port) {
		
		throw new UnsupportedOperationException(" Usinig getService(int src_port, int dst_port) method");
	}


	public Map<Integer, String> getPortNameMap() {
		return portNameMap;
	}


	public void setPortNameMap(Map<Integer, String> portNameMap) {
		this.portNameMap = portNameMap;
	}
	
}
