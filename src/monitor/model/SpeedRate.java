package monitor.model;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SpeedRate {

	
	public enum Speed{
		
		INPUT_SPEED,
		OUTPUT_SPEED,
		MAX_INPUT_SPEED,
		MAX_OUTPUT_SPEED;
	
		
/*
		SPEED_RATE;
			

		public void setInputSpeed(double speed) {current_input_speed = speed;};
		public double getInputSpeed() {return current_input_speed;};
		
		public void setOutputSpeed(double speed) {current_output_speed = speed;};
		public double getOutputSpeed() {return current_output_speed;};
		
		public void setMaxInputSpeed(double speed) {max_input_speed = speed;};
		public double getMaxInputSpeed() {return max_input_speed;};
		
		public void setMaxOutputSpeed(double speed) {max_output_speed = speed;};
		public double getMaxOutputSpeed() {return max_output_speed;};
		
		public void clear() {
			
			current_input_speed  = 0;
			current_output_speed = 0;
			max_input_speed = 0;
			max_output_speed = 0;
			
			
		}
		
		
		private static double current_input_speed;
		private static double current_output_speed;
		private static double max_input_speed;
		private static double max_output_speed;
		
		*/
	}
	
	
	private static final WeakHashMap<Enum<Speed>, Double> map = new WeakHashMap<>();
	
	
	public static void addInputSpeed(double speed) {
		
		map.put(Speed.INPUT_SPEED, speed);
		
	}
	
	public static void addOutputSpeed(double speed) {
		
		map.put(Speed.OUTPUT_SPEED, speed);
		
	}
	
	public static void addMaxInputSpeed(double speed) {
		
		map.put(Speed.MAX_INPUT_SPEED, speed);
		
	}
	
	public static void addMaxOutputSpeed(double speed) {
		
		map.put(Speed.MAX_OUTPUT_SPEED, speed);
		
	}
	
     public static double getInputSpeed() {
		
		return map.get(Speed.INPUT_SPEED);
		
	}
	
	public static double getOutputSpeed() {
		
		return map.get(Speed.OUTPUT_SPEED);
		
	}
	
	public static double getMaxInputSpeed() {
		
		return map.get(Speed.MAX_INPUT_SPEED);
		
	}
	
	public static double getMaxOutputSpeed() {
		
		return map.get(Speed.MAX_OUTPUT_SPEED);
		
	}
	
}
