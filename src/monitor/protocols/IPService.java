package monitor.protocols;

import java.util.Map;


public class IPService implements NetworkService{

	
    private Map<Integer, String> portNameMap;

    private static final Integer NULL = 0;

	
    public String getService(int port) {
		
		return portNameMap.getOrDefault(port, portNameMap.get(NULL));

    }
	
    public Map<Integer, String> getPortNameMap() {
	    
		return portNameMap;
    
    }

    public void setPortNameMap(Map<Integer, String> portNameMap) {
		
		this.portNameMap = portNameMap;
	
    }

    @Override
    public String getService(short port) {
		
		throw new UnsupportedOperationException(" Usinig getService(int port) method");
    }

    @Override
    public String getService(int src_port, int dst_port) {
		
		throw new UnsupportedOperationException(" Usinig getService(int port) method");
    }
	
	
}
