package monitor.network.utils;

public class OSType {

	
	public static boolean isWindows() {
		
		return System.getProperty("os.name").toLowerCase().matches(".*win.*");
	}
	
    public static boolean isLinux() {
		
		return System.getProperty("os.name").toLowerCase().matches(".*lin.*");
	}
	
}
