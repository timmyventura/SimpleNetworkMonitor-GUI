package monitor.model;


import org.jnetpcap.packet.PcapPacket;


import monitor.protocols.ARPService;
import monitor.protocols.EthernetService;
import monitor.protocols.ICMPService;
import monitor.protocols.IPService;
import monitor.protocols.TCPService;
import monitor.protocols.UDPService;
import monitor.protocols.ProtocolSet;

import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.network.Icmp;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

public class ReturnConcreteApplication implements PacketHandle {

	
	private static final Tcp tcp = (Tcp)ProtocolSet.TCP.getInstance();
	private static final Udp udp = (Udp)ProtocolSet.UDP.getInstance();
	private static final Ip4 ip4 = (Ip4)ProtocolSet.IPv4.getInstance();
	private static final Ethernet ether = (Ethernet)ProtocolSet.ETHERNET.getInstance();
	private static final Arp arp = (Arp)ProtocolSet.ARP.getInstance();
	private static final Icmp icmp = (Icmp)ProtocolSet.ICMP.getInstance();
	
	@Override
	public String packetHandle(PcapPacket packet) {
		
		if(packet.hasHeader(tcp)) {
			return packetHandle((Tcp)packet.getHeader(tcp));
		}
		if(packet.hasHeader(udp)) {
			return packetHandle((Udp)packet.getHeader(udp));
		}
		if(packet.hasHeader(ip4)) {
			return packetHandle((Ip4)packet.getHeader(ip4));
		}
		if(packet.hasHeader(ether)) {
			return packetHandle((Ethernet)packet.getHeader(ether));
		}
		return packetHandle((Ethernet)packet.getHeader(ether));
		
				
	}
	
	@Override
	public String packetHandle(Tcp packet) {
			
		return TCPService.getService(packet.source(), packet.destination());
	}

	@Override
	public String packetHandle(Udp packet) {
		
		return UDPService.getService(packet.source(), packet.destination());
	}

	@Override
	public String packetHandle(Ip4 packet) {
		
		return IPService.getService(packet.type());
	}

	@Override
	public String packetHandle(Arp packet) {
		
		return ARPService.getService(packet.getId());
	}

	@Override
	public String packetHandle(Icmp packet) {
		
		return ICMPService.getService(packet.getId());
	}

	@Override
	public String packetHandle(Ethernet packet) {
					
		return EthernetService.getService((short)packet.type());
	}

	

}