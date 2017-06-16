package monitor.view;

import java.util.Map;


public interface View {

	public void addObservation(Map<Object, Object> param);
	public void addObservation(double ... param);
	public void addObservation();
}
