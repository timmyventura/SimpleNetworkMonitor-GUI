package monitor.protocols;


import org.jnetpcap.packet.PcapPacket;

import monitor.protocols.NetworkService;

import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

public class NetworkServiceName implements NetworkApplicationName {

	
	private NetworkService udpService;
	private NetworkService tcpService;
	private NetworkService ipService;
	private NetworkService etherService;
	
	private  Tcp tcp = (Tcp)ProtocolSet.TCP.getInstance();
	private  Udp udp = (Udp)ProtocolSet.UDP.getInstance();
	private  Ip4 ip4 = (Ip4)ProtocolSet.IPv4.getInstance();
	private  Ethernet ether = (Ethernet)ProtocolSet.ETHERNET.getInstance();
	
	@Override
	public String getNetworkApplication(PcapPacket packet) {
	

		if(packet.hasHeader(tcp)) {
			return getNetworkApplication((Tcp)packet.getHeader(tcp));
		}
		if(packet.hasHeader(udp)) {
			return getNetworkApplication((Udp)packet.getHeader(udp));
		}
		if(packet.hasHeader(ip4)) {
			return getNetworkApplication((Ip4)packet.getHeader(ip4));
		}
		if(packet.hasHeader(ether)) {
			return getNetworkApplication((Ethernet)packet.getHeader(ether));
		}
		return getNetworkApplication((Ethernet)packet.getHeader(ether));
		
				
	}
	
	@Override
	public String getNetworkApplication(Tcp packet) {
			
		return tcpService.getService(packet.source(), packet.destination());
	}

	@Override
	public String getNetworkApplication(Udp packet) {
		
		return udpService.getService(packet.source(), packet.destination());
	}

	@Override
	public String getNetworkApplication(Ip4 packet) {
		
		return ipService.getService(packet.type());
	}

	@Override
	public String getNetworkApplication(Ethernet packet) {
				
		return etherService.getService((short)packet.type());
	}

	public NetworkService getUdpService() {
		return udpService;
	}

	public void setUdpService(NetworkService udpService) {
		this.udpService = udpService;
	}

	public NetworkService getTcpService() {
		return tcpService;
	}

	public void setTcpService(NetworkService tcpService) {
		this.tcpService = tcpService;
	}

	public NetworkService getIpService() {
		return ipService;
	}

	public void setIpService(NetworkService ipService) {
		this.ipService = ipService;
	}

	public NetworkService getEtherService() {
		return etherService;
	}

	public void setEtherService(NetworkService etherService) {
		this.etherService = etherService;
	}

	

}
