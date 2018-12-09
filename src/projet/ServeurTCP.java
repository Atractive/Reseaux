package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServeurTCP {
	
	public static void serv(int port){
		
		ServerSocket serveur = null;
		Socket client = null;
		
		BufferedReader in;
		PrintWriter out;
		String msg;
		
		
		
		final Scanner sc = new Scanner(System.in);
		
		// Création du Serveur
		try {
			serveur = new ServerSocket(port);
		} catch (IOException ex) {
			System.err.println("Le port " + port + " est illisible");
			System.exit(-1);
		}
		
		try {
			
			client = serveur.accept();
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
			
			while(true){
				
				msg = in.readLine();
				System.out.println("Client : " + msg);
				if (msg.equals("bye")){
					break;
				}
				
				out.println(sc.next());
				out.flush();
				
			}
			
		} catch (IOException ex) {
			System.err.println("Le port " + port + " est inaccessible");
		}

	}

	public static void main(String[] args) {
		serv(4444);
	}

}
