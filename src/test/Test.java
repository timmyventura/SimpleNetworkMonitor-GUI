package test;

import java.net.NetworkInterface;
import java.net.SocketException;

public class Test {

	
	public static void main(String [] args) throws SocketException {
		
		
		
		System.out.println(NetworkInterface.getByName("enp2s0").getDisplayName());
		
		
	}
	
}
