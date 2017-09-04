package monitor.network.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import monitor.logging.Logging;
import monitor.logging.Logging.MessageType;


public class ExternalAddress {

	private HttpURLConnection hpcon;
    private HTTPRequestParameters requestParameters;
	
	private String externalIp;
    
	public class HTTPRequestParameters{
		
		private String requestURL;
		private String method;
		private String accept;
		private String acceptEncoding;
		private String connection;
		
		public HTTPRequestParameters() {}
		
		public String getRequestURL() {
			return requestURL;
		}
		public void setRequestURL(String requestURL) {
			this.requestURL = requestURL;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public String getAccept() {
			return accept;
		}
		public void setAccept(String accept) {
			this.accept = accept;
		}
		public String getAcceptEncoding() {
			return acceptEncoding;
		}
		public void setAcceptEncoding(String acceptEncoding) {
			this.acceptEncoding = acceptEncoding;
		}

		public String getConnection() {
			return connection;
		}

		public void setConnection(String connection) {
			this.connection = connection;
		}
		
		
	}

	public HTTPRequestParameters getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(HTTPRequestParameters requestParameters) {
		this.requestParameters = requestParameters;
	}
	
	public ExternalAddress() {
		
	}
	
	
	public String getExternalIP()
    {
 
         try {  
        	 
	    	 hpcon = (HttpURLConnection)new URL(getRequestParameters().getRequestURL()).openConnection();
             hpcon.setUseCaches(false);
             hpcon.setRequestMethod(getRequestParameters().getMethod());
             hpcon.setRequestProperty("Accept", getRequestParameters().getAccept());
             hpcon.setRequestProperty("Accept-Encoding", getRequestParameters().getAcceptEncoding());
             hpcon.setRequestProperty("Connection", getRequestParameters().getConnection());

        try(BufferedReader in = new BufferedReader(new InputStreamReader(hpcon.getInputStream()))){
  
		      externalIp = in.readLine();
		      
        }
        
       } catch (IOException e) {

       	Logging.log(ExternalAddress.class, MessageType.ERROR, e);
       	
       	Logging.viewLogMessage(e, MessageType.ERROR);
       	
       	return "NULL";
	}
		return externalIp;
   
    }
	
}
