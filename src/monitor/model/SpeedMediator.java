package monitor.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;

import org.jnetpcap.protocol.lan.Ethernet;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;
import monitor.protocols.ProtocolSet;
import monitor.view.View;

public class SpeedMediator extends AbstractModel {

	private List<View> views = new ArrayList<View>();
	
	private static final Timer timer = new Timer();
    private static final int TIMEOUT = 1000;
	private static int IN_LEN;
	private static int OUT_LEN;
	private static int MAX_IN_LEN;
	private static int MAX_OUT_LEN;
	private byte[] dev_mac;
		
   
    public SpeedMediator(PcapIf net) {
    	
    	try {
    		
			dev_mac = net.getHardwareAddress();
		
    	} catch (IOException e) {
			
			Logging.log(this.getClass(), MessageType.ERROR, e);
		    Logging.viewLogMessage(e, MessageType.ERROR);
		}
	}
    
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
		
		Ethernet pack = (Ethernet)packet.getHeader(ProtocolSet.ETHERNET.getInstance());
		
		if(Arrays.equals(pack.source(), dev_mac)) {
			OUT_LEN+=wirelen;
		}
		else {
			IN_LEN+=wirelen;
		}
		
	}
	
    private void sendToView() {
		
    	
    	views.forEach(view -> view.addObservation());
     	clear();
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

				SpeedRate.addInputSpeed(getInputLength());
				SpeedRate.addOutputSpeed(getOutputLength());
				SpeedRate.addMaxInputSpeed(getMaxInputLength());
				SpeedRate.addMaxOutputSpeed(getMaxOutputLength());
				
				sendToView();
				
			}
		};
		
		timer.scheduleAtFixedRate(task, 0,  TIMEOUT);
		
		
		
     
	}

}
