package chiffree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class ClientTCP {

	public static void main(String[] args) {
		
		Socket client = null;
		
		BufferedReader in;
		PrintWriter out;
		String msg;
		
		SecretKey key;
		ImportKey ik;
		Cipher cipher;
		
		byte[] data;
		byte[] result;
		byte[] original;
		
		
		
		final Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.println("Entrez l'adresse IP du serveur");
            client = new Socket(sc.next(), 4444);
            
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            
            ik = new ImportKey();
            key = ik.getKey();
            cipher = Cipher.getInstance("AES");

            System.out.println("Connexion établie... Vous pouvez entrer votre message");
            while (true) {
                
            	cipher.init(Cipher.ENCRYPT_MODE,key);
            	data = sc.next().getBytes();
            	result = cipher.doFinal(data);
                out.println(DatatypeConverter.printBase64Binary(result));
                out.flush();

                cipher.init(Cipher.DECRYPT_MODE,key);
            	original = DatatypeConverter.parseBase64Binary(in.readLine());
                msg = new String(cipher.doFinal(original));
                System.out.println("Serveur : " + original + " -> " + msg);
              
            }
			
		} catch (UnknownHostException e) {
			System.err.println("Destination Unknown");
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Now to Investigate this IO issue");
			System.exit(-1);
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

}
