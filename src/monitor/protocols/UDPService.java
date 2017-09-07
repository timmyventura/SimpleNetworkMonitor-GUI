package monitor.protocols;

import java.util.Map;

public class UDPService implements NetworkService {

     private  Map<Integer, String> portNameMap;

     private static final Integer NULL = 0;
	
    public String getService(int port) {
		
		return portNameMap.getOrDefault(port, portNameMap.get(NULL));
		
    }
	 
    public  String getService(int src_port, int dst_port) {
		
		if(portNameMap.containsKey(src_port))
			return portNameMap.get(src_port);
		else
			if(portNameMap.containsKey(dst_port))
			 return portNameMap.get(dst_port);
			else
				return portNameMap.get(NULL);
    }
     
    public  Map<Integer, String> getPortNameMap() {
	       return portNameMap;
    }

    public void setPortNameMap(Map<Integer, String> portNameMap) {
	         this.portNameMap = portNameMap;
    }


    @Override 
    public String getService(short port) {
		
		throw new UnsupportedOperationException(" Usinig getService(int src_port, int dst_port) or getService(int port) methods"); 
    } 
	
}
