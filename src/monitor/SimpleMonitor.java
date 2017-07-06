package monitor;



import monitor.model.InformMediator;



import monitor.capture.PacketCapture;
import monitor.controller.FrameModelController;
import monitor.model.PieMediator;
import monitor.model.SpeedMediator;
import monitor.view.Frame;
import monitor.view.View;
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
	       
	       View  speedGraph = new SpeedGraph();
	       View  pieGraph = new PieGraph();
	       View  informGraph = new InformGraph(); 
	      
	       Frame mainFrame = new MainFrame(pieGraph, informGraph, speedGraph);
	       Frame initialFrame = new InitialFrame(controller);
	       	       
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