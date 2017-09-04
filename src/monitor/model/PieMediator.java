package monitor.model;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.WeakHashMap;

import org.jnetpcap.packet.PcapPacket;

import monitor.protocols.NetworkApplicationName;
import monitor.view.View;

public class PieMediator extends AbstractModel {

	
	private List<View> views;
	private Map<Object, Object> serviceValueMap = new WeakHashMap<Object, Object>();
	private NetworkApplicationName serviceName;
	
	private static final Timer timer = new Timer();
	private static final long TIMEOUT = 5000;

	
	public PieMediator() {
		
	}
	
	private void addToMap(String service, int length) {
		
		
		serviceValueMap.put(service, ((Double)serviceValueMap.getOrDefault(service, 0.0)+(double)length));
		
	}

	private void sendToView() {
		
		
		views.forEach(view -> view.addObservation(serviceValueMap));
		
	}
	
	
	@Override
	public void execute(PcapPacket packet) {
		
		addToMap(serviceName.getNetworkApplication(packet), packet.getCaptureHeader().wirelen());	
		
	}

	public void clear() {
		
		serviceValueMap.clear();
		
	}
	
	public void setViews(List<View> views) {
		
		this.views = views;
	}
	
	public List<View> getViews(){
		
		return views;
		
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
		
		  timer.scheduleAtFixedRate(task, 0, TIMEOUT);
		
		
	}

	public NetworkApplicationName getServiceName() {
		return serviceName;
	}

	public void setServiceName(NetworkApplicationName serviceName) {
		this.serviceName = serviceName;
	}
}
