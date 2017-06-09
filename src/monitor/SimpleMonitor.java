package monitor;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.jnetpcap.PcapIf;

import monitor.model.InformMediator;
import monitor.capture.PacketCapture;
import monitor.model.PieMediator;
import monitor.model.SpeedMediator;
import monitor.network.OSNetworkFactory;
import monitor.view.swing.InformGraph;
import monitor.view.swing.MainFrame;
import monitor.view.swing.PieGraph;
import monitor.view.swing.SpeedGraph;



public class SimpleMonitor {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws CaptureDeviceLookupException 
	 * @throws CaptureDeviceOpenException 
	 * @throws CapturePacketException 
	 */
	

	private static int TIMEOUT = 1000;
	public static void main(String[] args) throws IOException, InterruptedException {
		
		   PacketCapture pc = new PacketCapture();
	       List<PcapIf> interfaces = pc.getDevices();
	       PcapIf device = interfaces.get(12);
	        
	       pc.chooseDevice(device);
	      
	       PieMediator pm = new PieMediator();
	       SpeedMediator sm = new SpeedMediator(device);   
	       InformMediator im = new InformMediator(OSNetworkFactory.returnConcreteNetworkFactoryObject(System.getProperty("os.name"), device));
	       
	       SpeedGraph speedgraph = new SpeedGraph(60000);
	       PieGraph piegraph = new PieGraph();
	       InformGraph inform = new InformGraph();
	      
	       MainFrame frame = new MainFrame(piegraph, inform, speedgraph);
	       
		   pc.addMediator(sm);
		   pc.addMediator(pm);

		   sm.addView(speedgraph);
		   sm.addView(inform);
		   pm.addView(piegraph);
		   im.addView(inform);
	        
			frame.init();
			
			//NetstatHandler nh = new NetstatHandler();
			
	        ExecutorService exec = Executors.newFixedThreadPool(4);
	       // exec.execute(nh);
	        exec.execute(pc);
	        exec.execute(sm);
	        exec.execute(pm);
	}
}