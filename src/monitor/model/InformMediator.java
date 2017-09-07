package monitor.model;


import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;

import monitor.network.NetworkFactory;
import monitor.view.swing.InformGraph;
import monitor.view.View;

public class InformMediator extends AbstractModel {

    private InformGraph graph;
    private NetworkFactory networkFactory;
	
    public InformMediator() {
 	
    }
        
    public void initInform(PcapIf device) {
    	
    	
    	getNetworkFactory().setDevice(device);
    	graph.setIPToLocalIPField(getNetworkFactory().getLocalIP());
    	graph.setMaskToMaskField(getNetworkFactory().getNetworkMask());
    	graph.setDNSLabelsFields(getNetworkFactory().getNameServers());
    	graph.setHostnameToHostnameField(getNetworkFactory().getHostname()); 
    	graph.setIPToExternalIPField(getNetworkFactory().getExternalIP());
    	graph.setGatewayToGatewayField(getNetworkFactory().getDefaultGateway());
    	
    	graph.init();
    	
    	
    }

	@Override
    public void addView(View view) {
		
	  if(view instanceof monitor.view.swing.InformGraph) 
		{
         graph = (monitor.view.swing.InformGraph)view;
     
		}

     }

     @Override
     public boolean removeView(View view) {
		
		graph = null;
		
		return graph==null;
     }

     @Override
     public void execute(PcapPacket packet) {
		
             throw new UnsupportedOperationException();
		
      }

      @Override
      public void run() {
		
	    throw new UnsupportedOperationException();
		
      }

      public View getGraph() {
	      
		return graph;
	      
      }

      public void setGraph(InformGraph graph) {
	      
		this.graph = graph;
	      
      }

      public NetworkFactory getNetworkFactory() {
	      
		return networkFactory;
	      
      }

      public void setNetworkFactory(NetworkFactory networkFactory) {
	      
		this.networkFactory = networkFactory;
	      
      }

}
