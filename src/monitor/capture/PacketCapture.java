package monitor.capture;


import java.util.ArrayList;
import java.util.List;


import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacketHandler;

import monitor.model.Mediator;


public class PacketCapture implements Runnable {

	private static final List<Mediator> mediators = new ArrayList<Mediator>();
	private static final List<PcapIf>   alldevs   = new ArrayList<PcapIf>();
	private PcapIf device;
	private Pcap handle;
	private static final StringBuilder errbuf = new StringBuilder();
	
	public static final int DEFAULT_SNAPLEN = Pcap.DEFAULT_SNAPLEN;
	public static final int DEFAULT_TIMEOUT = System.getProperty("os.name").startsWith("W")?-1:0;
	public static final int DEFAULT_FLAG    = Pcap.MODE_PROMISCUOUS;
	

	public Pcap openHandle(PcapIf device, int snaplen, int flags, int timeout) {
		
		handle = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
		
		if (handle == null) {  
            System.err.printf("Error while opening device for capture: "  
                + errbuf.toString());  
            return null;  
        }  
		
		return handle;
		
	}
	
	public void closeHandle() {
		
		handle.close();
	
	}
	
    public List<PcapIf> getDevices() {
    	
    	 int r = Pcap.findAllDevs(alldevs, errbuf);  
	        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {  
	            System.err.printf("Can't read list of devices, error is %s", errbuf  
	                .toString());  
	            return null;  
	        }  
	        else
		return alldevs;
    }
    
    

	public void chooseDevice(PcapIf device) {
		
		this.device = device;
		
	}
	
	public PcapIf getChosenDevice() {
		
		return device;
	}
		
	public void addMediator(Mediator med) {
		
		mediators.add(med);
	}
	
	public boolean removeMediator(Mediator med) {
		
		return mediators.remove(med);
	}
	
	@Override
	public void run() {
		
			if(handle==null) handle = openHandle(device, DEFAULT_SNAPLEN, DEFAULT_FLAG, DEFAULT_TIMEOUT);
			
			PcapPacketHandler<String> packethandler = (packet, string) ->mediators.forEach(med->med.execute(packet));
			
			handle.loop(0, packethandler, null);
	}
}
