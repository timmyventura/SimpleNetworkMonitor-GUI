package monitor.controller;



import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.jnetpcap.PcapIf;

import monitor.capture.PacketCapture;
import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;
import monitor.model.InformMediator;
import monitor.model.PieMediator;
import monitor.model.SpeedMediator;
import monitor.network.OSNetworkFactory;
import monitor.network.utils.Devices;
import monitor.view.Frame;
import monitor.view.View;
import monitor.view.swing.InitialFrame;


public class FrameModelController {

	private Frame mainFrame;
	private Frame initialFrame;
	
	private PacketCapture packetCapture;
	
	private PieMediator pieMediator;
	private SpeedMediator speedMediator;
	private InformMediator informMediator;
	
	private View speedGraph;
	private View pieGraph;
	private View informGraph;
	
	private String selectedDevice;

	private static final ExecutorService exec = Executors.newFixedThreadPool(3);
	
	public void runningInitialFrame() {
		
		initialFrame.init();
		initialFrame.setVisible(true);	
		setInitialComboModel(Devices.getDevicesName());
		
	}
	
	public String getSelectedDevice() {
		return selectedDevice;
	}

	public void setSelectedDevice(String chosenDevice) {
		this.selectedDevice = chosenDevice;
	}
	
	public void runningMainFrame() {
		
		initialFrame.setVisible(false);
		
		PcapIf device = Devices.getSelectedDevice(selectedDevice);
		packetCapture.chooseDevice(device);
		
		packetCapture.addMediator(speedMediator);
		packetCapture.addMediator(pieMediator);

		speedMediator.addView(speedGraph);
		speedMediator.addView(informGraph);
		pieMediator.addView(pieGraph);
		informMediator.addView(informGraph);

			
			try {
				
				speedMediator.setHardwareAddress(device.getHardwareAddress());
				
			} catch (IOException e) {
				
		      Logging.log(this.getClass(),MessageType.ERROR, e);
		      Logging.viewLogMessage(e, MessageType.ERROR);
				
			}
			informMediator.initInform(OSNetworkFactory.returnConcreteNetworkFactoryObject(device));
			
		
		
		mainFrame.init();
		
		exec.execute(packetCapture);
		exec.execute(speedMediator);
		exec.execute(pieMediator);
		
	}
	
	
	public void setInitialComboModel(String [] interfaces) {
		
		((InitialFrame)initialFrame).setComboModel(interfaces);
		
	}

	

	public Frame getMainFrame() {
		
		return mainFrame;
		
	}

	public void setMainFrame(Frame mainFrame) {
		
		this.mainFrame = mainFrame;
		
	}

	public Frame getInitialFrame() {
		
		return initialFrame;
		
	}

	public void setInitialFrame(Frame initialFrame) {
		
		this.initialFrame = initialFrame;
		
	}


	public PacketCapture getPacketCapture() {
		
		return packetCapture;
	}



	public void setPacketCapture(PacketCapture packetCapture) {
		
		this.packetCapture = packetCapture;
	
	}

	public PieMediator getPieMediator() {
		return pieMediator;
	}

	public void setPieMediator(PieMediator pieMediator) {
		this.pieMediator = pieMediator;
	}

	public SpeedMediator getSpeedMediator() {
		return speedMediator;
	}

	public void setSpeedMediator(SpeedMediator speedMediator) {
		this.speedMediator = speedMediator;
	}

	public InformMediator getInformMediator() {
		return informMediator;
	}

	public void setInformMediator(InformMediator informMediator) {
		this.informMediator = informMediator;
	}

	public View getSpeedGraph() {
		return speedGraph;
	}

	public void setSpeedGraph(View speedGraph) {
		this.speedGraph = speedGraph;
	}

	public View getPieGraph() {
		return pieGraph;
	}

	public void setPieGraph(View pieGraph) {
		this.pieGraph = pieGraph;
	}

	public View getInformGraph() {
		return informGraph;
	}

	public void setInformGraph(View informGraph) {
		this.informGraph = informGraph;
	}

	

	
}
