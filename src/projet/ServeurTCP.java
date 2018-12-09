package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class ServeurTCP {
	
	public static void serv(int port) {
		
		ServerSocket serveur = null;
		Socket client = null;
		
		BufferedReader in;
		PrintWriter out;
		String msg;
		byte[] data;
		
		SecretKey key;
		ImportKey ik;
		Cipher cipher;
		
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
			
			ik = new ImportKey();
			key = ik.getKey();
			cipher = Cipher.getInstance("AES");
			
			while(true){
				
				cipher.init(Cipher.DECRYPT_MODE,key);
				data = in.readLine().getBytes("UTF-8");
				msg = new String (cipher.doFinal(data));
				
				System.out.println("Client : " + data + " -> " + msg);
				if (msg.equals("bye")){
					break;
				}
				
				out.println(sc.next());
				
			}
			
		} catch (IOException ex) {
			System.err.println("Le port " + port + " est inaccessible");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		serv(4444);
	}

}
