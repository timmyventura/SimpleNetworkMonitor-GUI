package monitor.view;

import java.util.Map;


public interface View {

	public void addObservation(Map<Object, Object> param);
	public void addObservation(double input_length, 
			           double output_length, 
			           double max_input_length,
			           double max_output_length);
}
