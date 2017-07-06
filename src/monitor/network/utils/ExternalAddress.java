package monitor.network.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;


public class ExternalAddress {

	private static HttpURLConnection hpcon;
	private static final String request = "https://myexternalip.com/raw";
	private static final String method = "GET";
	private static String ext_ip;
	
	
	public static String getExternalIP()
    {
 
         try {  
        	 
	    	 hpcon = (HttpURLConnection)new URL(request).openConnection();
             hpcon.setUseCaches(false);
             hpcon.setRequestMethod(method);
             hpcon.setRequestProperty("Accept", "text/html");
             hpcon.setRequestProperty("Accept-Encoding", "utf-8");
             hpcon.setRequestProperty("Connection", "close");

        try(BufferedReader in = new BufferedReader(new InputStreamReader(hpcon.getInputStream()))){
  
		      ext_ip = in.readLine();
		      
        }
        
       } catch (IOException e) {

       	Logging.log(ExternalAddress.class, MessageType.ERROR, e);
       	
       	Logging.viewLogMessage(e, MessageType.ERROR);
       	
       	return "NULL";
	}
		return ext_ip;
   
    }
	
}
