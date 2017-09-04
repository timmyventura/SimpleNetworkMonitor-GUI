package monitor.logging;


import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;;

public class Logging {
	
	private static final ConcurrentHashMap<Class<? extends Object>, Logger> map = new ConcurrentHashMap<>();
		
	
	public enum MessageType{
		
		INFO{
			
			public void writeTextIntoLogFile(String text, Logger logging) {
				
		        logging.info(text);
				
			}

			@Override
			public void showLogInMessageDialog(String text) {
				
				JOptionPane.showMessageDialog(null, text, "INFO", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
		}, 
		
		ERROR{
			
            public void writeTextIntoLogFile(String text, Logger logging) {
				
		        logging.error(text);;
				
			}

			@Override
			public void showLogInMessageDialog(String text) {
				

				JOptionPane.showMessageDialog(null, text, "ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
	
			
		};
		
		public abstract void writeTextIntoLogFile(String text, Logger logging);
		
		public abstract void showLogInMessageDialog(String text);
	}
	
	
	public static void log(Class<? extends Object> classname, MessageType type, Throwable throwable) {

			log(classname, type, throwable.toString() + " at " + throwable.getStackTrace()[0]);
	
		
	}
	
	public static void log(Class<? extends Object> classname, MessageType type, String text) {
	     
		Logger logging = map.putIfAbsent(classname, LoggerFactory.getLogger(classname));
		
		if(logging == null)
			type.writeTextIntoLogFile(text, map.get(classname));
		else
			type.writeTextIntoLogFile(text, logging);
			
	}
	
	
	public static void viewLogMessage(String text, MessageType type) {
		
		type.showLogInMessageDialog(text);

	}
	
    public static void viewLogMessage(Throwable throwable, MessageType type) {

			viewLogMessage(throwable.toString() + " at " + throwable.getStackTrace()[0], type);

	}
}
