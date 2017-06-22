package monitor.network.utils;

import java.util.ArrayList;
import java.util.List;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;

public class Devices {

	
	private static final List<PcapIf>   alldevs   = new ArrayList<PcapIf>();
	private static final StringBuilder errbuf = new StringBuilder();
	
	 public static List<PcapIf> getDevices() {
	    	
    	 int r = Pcap.findAllDevs(alldevs, errbuf);  
	        if (r == Pcap.NOT_OK || alldevs.isEmpty()) {  
	            System.err.printf("Can't read list of devices, error is %s", errbuf  
	                .toString());  
	            return null;  
	        }  
	        else
	        	
		return alldevs;
    }
	 
	 public static String [] getDevicesName() {
	    	
    	List<PcapIf> devs = getDevices();
    	int size = devs.size()+1;
    	String [] devs_names = new String [size];
    	for(PcapIf dev : devs) {

    		devs_names[--size] = OSType.isWindows()?dev.getDescription():dev.getName();
    	}        
    	devs_names[--size] = "  ";
		return devs_names;
    }
	 
	 public static PcapIf getSelectedDevice(String device) throws NullPointerException {
			
		 PcapIf interf = null;
		 
		 for(PcapIf dev : alldevs) {
			 
			 if((OSType.isWindows()?dev.getDescription():dev.getName()).equals(device))
				 
				 interf = dev;
		 }
		 return interf;

	}
	
}
