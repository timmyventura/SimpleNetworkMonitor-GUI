package monitor.capture;

import java.util.List;

import monitor.model.Mediator;


/*
 * The interface required to register Intermediates classes that deal with the processing of network frames, 
 * and interaction with View classes
 */
public interface MediatorStorage {

	/*
	 * This method is allow to add intermediates objects
	 */
    public void addMediator(Mediator mediator); 
	/*
	 * This method is allow to remove intermediates objects
	 */
	public boolean removeMediator(Mediator mediator);
	/*
	 * This method is allow to return intermediates objects
	 */
	public List<Mediator> getMediators();
	
	
}
