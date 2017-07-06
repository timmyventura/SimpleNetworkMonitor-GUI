package monitor.model;


import org.jnetpcap.packet.PcapPacket;

import monitor.network.NetworkFactory;
import monitor.view.swing.InformGraph;
import monitor.view.View;

public class InformMediator extends AbstractModel {

	private InformGraph graph;

    
    public InformMediator() {
 	
    }
        
    public void initInform(NetworkFactory factory) {
    	
    	graph.setIPToLocalIPField(factory.getLocalIP());
    	graph.setMaskToMaskField(factory.getNetworkMask());
    	graph.setDNSLabelsFields(factory.getNameServers());
    	graph.setHostnameToHostnameField(factory.getHostname()); 
    	graph.setIPToExternalIPField(factory.getExternalIP());
    	graph.setGatewayToGatewayField(factory.getDefaultGateway());
    	
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

}
