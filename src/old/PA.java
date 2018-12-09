package old;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PA {

	public static void main(String[] args) {

		ServerSocket server;
		Socket client;
		
		BufferedReader in;
		PrintWriter out;
		String msg;
		
		try {
			
			server = new ServerSocket(4444);
			client = server.accept();
			
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
			msg = in.readLine();
			
			while (msg!=null) {
				System.out.println("Client : " + msg);
				msg = in.readLine();
			}
			
			out.println("coucou");
			out.flush();
			
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

}
