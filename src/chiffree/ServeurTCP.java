package chiffree;

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
import javax.xml.bind.DatatypeConverter;

public class ServeurTCP {
	
	public static void serv(int port) {
		
		ServerSocket serveur = null;
		Socket client = null;
		
		BufferedReader in;
		PrintWriter out;
		String msgr;
		String msgc;
		
		SecretKey key;
		ImportKey ik;
		Cipher cipher;
		
		byte[] data;
		byte[] result;
		byte[] original;
		
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
			
			// Recuperation de la cle et creation du cipher
			ik = new ImportKey();
			key = ik.getKey();
			cipher = Cipher.getInstance("AES");

            while (true) {
            	
            	// Decryptage du message et affichage dans la console
            	cipher.init(Cipher.DECRYPT_MODE,key);
            	msgr = in.readLine();
            	original = DatatypeConverter.parseBase64Binary(msgr);
                msgc = new String(cipher.doFinal(original));
                System.out.println("Client : " + msgr + " -> " + msgc);
                
                // Si le message recu est "bye", on sort de la boucle
                if (msgc.equals("bye")) {
                    break;
                }
                
                // Encryptage du message et envoie
                cipher.init(Cipher.ENCRYPT_MODE,key);
            	data = sc.nextLine().getBytes();
            	result = cipher.doFinal(data);
                out.println(DatatypeConverter.printBase64Binary(result));
                out.flush();
                   
            }
			
		} catch (IOException ex) {
			System.err.println("Le port " + port + " est inaccessible");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		serv(4444);
	}

}
