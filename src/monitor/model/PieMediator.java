package monitor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import org.jnetpcap.packet.PcapPacket;

import monitor.view.View;

public class PieMediator implements Mediator, Model, Runnable {

	
	private List<View> views = new ArrayList<View>();
	private Map<Object, Object> map = new WeakHashMap<>();
	private PacketHandle packetHandler;
	
	private static final Timer timer = new Timer();
	private static final long TIMEOUT = 5000;
	private static double LOAD = 0.75;
	private static double LIMIT = Integer.MAX_VALUE*LOAD;
	
	public PieMediator() {
		
		if(packetHandler==null) packetHandler = new ReturnConcreteApplication();
	}
	
	public void setPacketVisitor(PacketHandle packetHandle) {
		
		this.packetHandler=packetHandle;
	}
	
	public PacketHandle getPacketVisitor() {
		
		return packetHandler;
	}
	
	private void addToMap(String service, int length) {
		
		
		map.put(service, ((Double)map.getOrDefault(service, 0.0)+(double)length));
		
		if((Double)map.get(service)>LIMIT) reduceMap();
		
	}
	
	private void reduceMap() {
		
		map.forEach((key, value) -> {map.put(key, (Double)map.get(key)-((Double)map.get(key)*LIMIT));});
				  
	}
	
	private void sendToView() {
		
		views.forEach(view -> view.addObservation(map));
		clear();
	}
	
	
	@Override
	public void execute(PcapPacket packet) {
		
		addToMap(packetHandler.packetHandle(packet), packet.getCaptureHeader().wirelen());	
		
	}

	public void clear() {
		
       map.clear();
		
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
		
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				sendToView();
				
			}
			
		};
		
		  timer.scheduleAtFixedRate(task, 0, TIMEOUT);
		
		
	}
}
