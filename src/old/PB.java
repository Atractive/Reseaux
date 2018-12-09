package old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PB {

	public static void main(String[] args) {
		
		Socket client;
		
		BufferedReader in;
		PrintWriter out;
		String msg;
		
		try {
			
			client = new Socket("localhost",4444);
			
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
			out.println("salut");
			out.flush();
			msg = in.readLine();
			
			while (msg!=null) {
				System.out.println("Serveur : " + msg);
				msg = in.readLine();
			}
			
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
