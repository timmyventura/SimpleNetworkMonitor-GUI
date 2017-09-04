package monitor.protocols;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;


public interface NetworkApplicationName {

	public String getNetworkApplication(PcapPacket packet);
	public String getNetworkApplication(Tcp packet);
	public String getNetworkApplication(Udp packet);
	public String getNetworkApplication(Ip4 packet);
	public String getNetworkApplication(Ethernet packet);
	
}
