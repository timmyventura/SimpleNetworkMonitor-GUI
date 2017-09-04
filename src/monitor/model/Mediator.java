package monitor.model;

import org.jnetpcap.packet.PcapPacket;



public interface Mediator {

	public void execute(PcapPacket packet);

}
