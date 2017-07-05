package monitor;



import monitor.model.InformMediator;

import java.util.concurrent.Executors;

import monitor.capture.PacketCapture;
import monitor.controller.FrameModelController;
import monitor.model.PieMediator;
import monitor.model.SpeedMediator;
import monitor.network.OSNetworkFactory;
import monitor.network.utils.Devices;
import monitor.view.swing.InformGraph;
import monitor.view.swing.InitialFrame;
import monitor.view.swing.MainFrame;
import monitor.view.swing.PieGraph;
import monitor.view.swing.SpeedGraph;



public class SimpleMonitor {

	

	public static void main(String[] args){
		
		 
		   FrameModelController controller = new FrameModelController();
	      
		   
		   PacketCapture packetCapture = new PacketCapture();
	       PieMediator pieMediator = new PieMediator();
	       SpeedMediator speedMediator = new SpeedMediator();   
	       InformMediator informMediator = new InformMediator();
	       
	       SpeedGraph speedGraph = new SpeedGraph();
	       PieGraph  pieGraph = new PieGraph();
	       InformGraph informGraph = new InformGraph();
	      
	       MainFrame mainFrame = new MainFrame(pieGraph, informGraph, speedGraph);
	       InitialFrame initialFrame = new InitialFrame(controller);
	       	       
	       controller.setPacketCapture(packetCapture);
	       controller.setPieMediator(pieMediator);
	       controller.setSpeedMediator(speedMediator);
	       controller.setInformMediator(informMediator);
	       
	       controller.setSpeedGraph(speedGraph);
	       controller.setPieGraph(pieGraph);
	       controller.setInformGraph(informGraph);
	       
	       controller.setMainFrame(mainFrame);
	       controller.setInitialFrame(initialFrame);
	      
	       
	       
	       controller.runningInitialFrame();
	       
		
	
		 
	}
}