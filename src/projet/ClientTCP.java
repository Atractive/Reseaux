package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {

	public static void main(String[] args) {
		
			
		Socket client = null;
			
		BufferedReader in;
		PrintWriter out;
		String msg;
		
		try {
			
			// "localhost" si on travaille en local
			// L'addresse IP si on travaille en distant
			client = new Socket("localhost",4444);
			
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
			
			out.println("coucou");
			
			// On ferme le serveur et le client
			out.println("bye");
			in.close();
			out.close();
			client.close();
			
		} catch (UnknownHostException e) {
			System.err.println("Destination Unknown");
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Now to Investigate this IO issue");
			System.exit(-1);
		}

	}

}
