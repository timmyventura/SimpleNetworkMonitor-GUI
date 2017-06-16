package monitor.capture;

import java.util.List;

import monitor.model.Mediator;

public interface Capture {

    public void addMediator(Mediator mediator); 
	
	public boolean removeMediator(Mediator mediator);
	
	public List<Mediator> getMediators();
	
	
}
