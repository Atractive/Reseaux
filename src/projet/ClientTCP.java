package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ClientTCP {

	public static void main(String[] args) {
		
		Socket client = null;
		
		BufferedReader in;
		PrintWriter out;
		String msg;
		
		SecretKey key;
		ImportKey ik;
		Cipher cipher;
		
		try {
			
			// "localhost" si on travaille en local
			// L'addresse IP si on travaille en distant
			client = new Socket("localhost",4444);
			
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(),true);
			
			ik = new ImportKey();
			key = ik.getKey();
			cipher = Cipher.getInstance("AES");
			
			cipher.init(Cipher.ENCRYPT_MODE,key);
			
			out.println(cipher.doFinal("coucou".getBytes("UTF-8")));
			out.flush();
			
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
