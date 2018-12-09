package old;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class P2 {

	public static void main(String[] args) {

		InetAddress addr;
		
		try {
			addr = InetAddress.getByName("192.168.1.15");
			if (addr.isReachable(1000)) {
				System.out.println("It's reachable !!");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
