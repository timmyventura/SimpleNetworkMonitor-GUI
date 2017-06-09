package monitor.model;


import monitor.network.NetworkFactory;
import monitor.view.swing.InformGraph;
import monitor.view.View;

public class InformMediator implements Model {

	
	private InformGraph graph;
	private NetworkFactory factory;

     	
    public InformMediator(NetworkFactory factory) {
    	   
    	this.factory = factory;

    }
    
    private void init() {
    	
    	graph.setIPToLocalIPField(factory.getLocalIP());
    	graph.setMaskToMaskField(factory.getNetworkMask());
    	graph.setDNSLabelsFields(factory.getNameServers());
    	graph.setHostnameToHostnameField(factory.getHostname()); 
    	graph.setIPToExternalIPField(factory.getExternalIP());
    	graph.setGatewayToGatewayField(factory.getDefaultGateway());
    	
    	
    }
    
   	   
	@Override
	public void addView(View view) {
		
      graph = (InformGraph)view;
      init();
      graph.init();
      
      

	}

	@Override
	public boolean removeView(View view) {
		// TODO Auto-generated method stub
		graph = null;
		
		return graph==null;
	}

}
