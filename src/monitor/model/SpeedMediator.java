package monitor.model;


import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.protocol.lan.Ethernet;
import monitor.protocols.ProtocolSet;
import monitor.view.View;

public class SpeedMediator extends AbstractModel {

	private List<View> views;
	
	private static final Timer timer = new Timer();
	
    private static final int TIMEOUT = 1000;
	private int IN_LEN;
	private int OUT_LEN;
	private int MAX_IN_LEN;
	private int MAX_OUT_LEN;
	private byte[] dev_mac;
    
	  
    public SpeedMediator() {

	}
    
    public SpeedMediator(byte [] net) {
    	
    	setHardwareAddress(net);
    	
	}
    
    public void setHardwareAddress(byte[] mac) {
    	
    	dev_mac = mac;
    	
    }
    
    public byte [] getHardwareAddressBytes() {
    	
    	return dev_mac;
    	
    }
    
    public String getHardwareAddressString() {
    	
    	return String.format("%x:%x:%x:%x:%x:%x", dev_mac);
    	
    }
	
	@Override
	public void execute(PcapPacket packet) {
	   	
		int wirelen = packet.getCaptureHeader().wirelen();
			
		if(compareSrcMacWithInterfaceMac(packet)) {
			
			OUT_LEN+=wirelen;
		}
		else {
			IN_LEN+=wirelen;
		}
		
	}
	
	private Ethernet getEtherHeader(PcapPacket packet) {
		
        return (Ethernet)packet.getHeader((Ethernet)ProtocolSet.ETHERNET.getInstance());

	}
	
	private boolean compareSrcMacWithInterfaceMac(PcapPacket packet) {
		
		return Arrays.equals(getEtherHeader(packet).source(), dev_mac);
		
	}
	
    private void sendToView() {
		
    	
    	views.forEach(view -> view.addObservation(getInputLength(), 
    			                                  getOutputLength(),
    			                                  getMaxInputLength(),
    			                                  getMaxOutputLength()));
  
     	
	}
   
    private double getInputLength() {
        	
    	
    	return (double)IN_LEN*8/1024;
    }
    
    private double getOutputLength() {
    	
    	
    	return (double)OUT_LEN*8/1024;
    }
    
    private double getMaxInputLength() {
    	
    	if(MAX_IN_LEN<=IN_LEN) MAX_IN_LEN=IN_LEN;
    	
    	
        return (double)MAX_IN_LEN*8/1024;
    	
    }
    
    private double getMaxOutputLength() {
    	
    	if(MAX_OUT_LEN<=OUT_LEN) MAX_OUT_LEN=OUT_LEN;
    	
        return (double)MAX_OUT_LEN*8/1024;
    	
    }

	public void clear() {
		
		IN_LEN=0;
		OUT_LEN=0;
		
	}

	@Override
	public void addView(View view) {
		
		views.add(view);
		
	}

	@Override
	public boolean removeView(View view) {
	
		return views.remove(view);
	}

	@Override
	public void run() {
		
		
		
		final TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
			
				sendToView();
				clear();
			}
		};
		
		timer.scheduleAtFixedRate(task, 0,  TIMEOUT);
		
     
	}
	
	
	public void setViews(List<View> views) {
		
		this.views = views;
	}
	
	public List<View> getViews(){
		
		return views;
		
	}

}
