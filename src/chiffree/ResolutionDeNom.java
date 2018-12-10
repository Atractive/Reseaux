package chiffree;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ResolutionDeNom {

	public static void main(String[] args) {

		InetAddress address;
		
		try {
			address = InetAddress.getLocalHost();
			System.out.println("Nom : " + address.getHostName());
			System.out.println("Adresse IP : " + address.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
