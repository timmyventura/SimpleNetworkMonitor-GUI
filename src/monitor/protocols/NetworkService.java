package monitor.protocols;

public interface NetworkService {

	
	public String getService(int port);
	public String getService(short port);
	public String getService(int src_port, int dst_port);
	
	
}
