package monitor.protocols;

import java.util.Map;

public class EthernetService implements NetworkService{

	
    private Map<Short, String> portNameMap;
    
    private static final Short NULL = 0x0000;
		
    public Map<Short, String> getPortNameMap() {
		return portNameMap;
    }

    public void setPortNameMap(Map<Short, String> portNameMap) {
		
		this.portNameMap = portNameMap;
    }
	
    public String getService(short port) {
		
		return portNameMap.getOrDefault(port, portNameMap.get(NULL));		
		
    }

    @Override
    public String getService(int port) {

		throw new UnsupportedOperationException(" Usinig getService(int port) method");
    }

    @Override
    public String getService(int src_port, int dst_port) {

		throw new UnsupportedOperationException(" Usinig getService(int port) method");
    }
	
}
