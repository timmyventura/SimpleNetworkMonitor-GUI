package monitor.controller;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jnetpcap.PcapIf;

import monitor.capture.PacketCapture;

import monitor.model.InformMediator;
import monitor.model.PieMediator;
import monitor.model.SpeedMediator;
import monitor.network.OSNetworkFactory;
import monitor.network.utils.Devices;
import monitor.view.swing.InformGraph;
import monitor.view.swing.InitialFrame;
import monitor.view.swing.MainFrame;
import monitor.view.swing.PieGraph;
import monitor.view.swing.SpeedGraph;

public class FrameModelController {

	private MainFrame mainFrame;
	private InitialFrame initialFrame;
	
	private PacketCapture packetCapture;
	
	private PieMediator pieMediator;
	private SpeedMediator speedMediator;
	private InformMediator informMediator;
	
	private SpeedGraph speedGraph;
	private PieGraph pieGraph;
	private InformGraph informGraph;
	
	private String selectedDevice;

	private static final ExecutorService exec = Executors.newFixedThreadPool(3);
	
	public FrameModelController() {
		
		
	}
	
	public void runningInitialFrame() {
		
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
			informMediator.initInform(OSNetworkFactory.returnConcreteNetworkFactoryObject(System.getProperty("os.name"), device));
			//informMediator.setNetworkFactory(OSNetworkFactory.returnConcreteNetworkFactoryObject(System.getProperty("os.name"), device));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mainFrame.init();
		
		exec.execute(packetCapture);
		exec.execute(speedMediator);
		exec.execute(pieMediator);
		
	}
	
	
	public void setInitialComboModel(String [] interfaces) {
		
		initialFrame.setComboModel(interfaces);
		
	}

	

	public MainFrame getMainFrame() {
		
		return mainFrame;
		
	}

	public void setMainFrame(MainFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
	}

	public InitialFrame getInitialFrame() {
		
		return initialFrame;
		
	}

	public void setInitialFrame(InitialFrame initialFrame) {
		
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

	public SpeedGraph getSpeedGraph() {
		return speedGraph;
	}

	public void setSpeedGraph(SpeedGraph speedGraph) {
		this.speedGraph = speedGraph;
	}

	public PieGraph getPieGraph() {
		return pieGraph;
	}

	public void setPieGraph(PieGraph pieGraph) {
		this.pieGraph = pieGraph;
	}

	public InformGraph getInformGraph() {
		return informGraph;
	}

	public void setInformGraph(InformGraph informGraph) {
		this.informGraph = informGraph;
	}

	

	
}
