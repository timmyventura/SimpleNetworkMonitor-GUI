package monitor.model;

import java.util.WeakHashMap;


public class SpeedRate {

	
	public enum Speed{
		
		INPUT_SPEED,
		OUTPUT_SPEED,
		MAX_INPUT_SPEED,
		MAX_OUTPUT_SPEED;
		
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
