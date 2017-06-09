package monitor.protocols;

import org.jnetpcap.packet.JHeader;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.network.Ip6;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

public enum ProtocolSet {

	ETHERNET(new Ethernet()),
	IPv4(new Ip4()),
	IPv6(new Ip6()),
	ICMP(new Icmp()),
	TCP(new Tcp()),
	UDP(new Udp()),
	ARP(new Arp());
	
	private JHeader header;
	
	private ProtocolSet(JHeader header) {
		
		this.header = header;
		
	}
	
	public JHeader getInstance() {
		
		return header;
	}
	
}
