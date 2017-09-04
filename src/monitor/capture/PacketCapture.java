package monitor.capture;

import java.util.List;


import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacketHandler;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;
import monitor.model.Mediator;

/*
 * PacketCapture class implements MediatorStorage and Runnbale interfaces. The main task of the class is storing 
 * the instance of the packet trapper, copying the frame arriving on the network card and transferring it to the 
 * intermediaries for further processing.
 * Also, this class stores an instance of the interface(which is an instance of PcapIf class) from which packages are copied, 
 * and the necessary attributes to start the listening process, such as DEFAULT_SNAPLEN, DEFAULT_TIMEOUT, DEFAULT_FLAG/
 */

public class PacketCapture implements MediatorStorage, Runnable {

	/*
	 * List of Mediator class objects.
	 */
	private List<Mediator> mediators;
	/*
	 * Intance of PcapIf class
	 */
	private PcapIf device;
	/*
	 * Instance of Pcap class
	 */
	private Pcap catcher;
	
	/*
	 * errbuf is a variable that store error messages, there may be in openHandle() method.
	 */
	private static final StringBuilder errbuf = new StringBuilder();
	/*
	 * Description taken from official manpage of pcap library(http://www.tcpdump.org/manpages/pcap.3pcap.html):
	 * 
	 * If, when capturing, you capture the entire contents of the packet, that requires more CPU time to copy the packet 
	 * to your application, more disk and possibly network bandwidth to write the packet data to a file, and more disk space to save the packet. 
	 * If you don't need the entire contents of the packet - for example, if you are only interested in the TCP headers of packets - you can set 
	 * the "snapshot length" for the capture to an appropriate value. If the snapshot length is set to snaplen, and snaplen is less than the size 
	 * of a packet that is captured, only the first snaplen bytes of that packet will be captured and provided as packet data. 
     * A snapshot length of 65535 should be sufficient, on most if not all networks, to capture all the data available from the packet. 
     * Pcap.DEFAULT_SNAPLEN is 65535;
	 */
	private int DEFAULT_SNAPLEN = Pcap.DEFAULT_SNAPLEN;
	/*
	 * Description taken from official manpage of pcap library(http://www.tcpdump.org/manpages/pcap.3pcap.html):
	 *  
	 */	
	private int DEFAULT_TIMEOUT = System.getProperty("os.name").toLowerCase().contains("win")?-1:0;
	private int DEFAULT_FLAG    = Pcap.MODE_PROMISCUOUS;
	
	
	
	public PacketCapture() {

	}

	public Pcap openHandle(PcapIf device, int snaplen, int flags, int timeout) {
		
		catcher = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
		
		if (catcher == null) {  
			
			String err_message = String.format("Can't read list of devices, error is", errbuf.toString());
        	
        	Logging.log(this.getClass(), MessageType.ERROR, err_message);
        	
        	Logging.viewLogMessage(err_message, MessageType.ERROR);
            
            return null;    
        }  
		
		return catcher;
		
	}
	
	public void closeHandle() {
		
		catcher.close();
	
	}
	
	public void setMediators(List<Mediator> mediators) {
		
		this.mediators = mediators;
		
	}

	public void chooseDevice(PcapIf device) {
		
		this.device = device;
		
	}

	public void addMediator(Mediator med) {
		
		mediators.add(med);
	}
	
	public boolean removeMediator(Mediator med) {
		
		return mediators.remove(med);
	}
	
	public List<Mediator> getMediators(){
		
		return mediators;
	}
	
    public void setDefaultSnaplen(int defaultSnaplen) {
		
		DEFAULT_SNAPLEN = defaultSnaplen;
		
	}
	
	public int  getDefaultSnaplen() {
		
		return DEFAULT_SNAPLEN;
	}
	
    public void setDefaultTimeout(int defaultTimeout) {
		
		DEFAULT_TIMEOUT = defaultTimeout;
		
	}
	
	public int  getDefaultTimeout() {
		
		return DEFAULT_TIMEOUT;
	}
	
   public void setDefaultFlag(int defaultFlag) {
		
		DEFAULT_FLAG = defaultFlag;
		
	}
	
	public int  getDefaultFlag() {
		
		return DEFAULT_FLAG;
	}
	
	@Override
	public void run() {
		
			if(catcher==null) catcher = openHandle(device, getDefaultSnaplen(), getDefaultFlag(), getDefaultTimeout());
			PcapPacketHandler<String> packethandler = (packet, string) ->mediators.forEach(med->med.execute(packet));
			
			catcher.loop(0, packethandler, null);
	}
}
