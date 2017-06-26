package monitor.model;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;


public interface PacketHandle {

	public String packetHandle(PcapPacket packet);
	public String packetHandle(Tcp packet);
	public String packetHandle(Udp packet);
	public String packetHandle(Ip4 packet);
	public String packetHandle(Ethernet packet);
	
}
