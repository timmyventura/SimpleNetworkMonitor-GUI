package monitor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import monitor.controller.FrameModelController;


/*
 * Main class of application
 */

public class SimpleMonitor {

	/*
	 * Main method in which application context is called. Then the object of FrameModelController class derived from bean container.
	 * Then occurs runningInitialFrame() method which is mapped initial form.
	 */

	public static void main(String[] args){
		
	     
		@SuppressWarnings("resource")
		ApplicationContext context = new FileSystemXmlApplicationContext("context/main-context.xml");
		FrameModelController controller = (FrameModelController)context.getBean(FrameModelController.class);   
        controller.runningInitialFrame();

	      
		 
	}
}