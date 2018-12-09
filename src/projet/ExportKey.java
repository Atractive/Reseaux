package projet;

import java.io.PrintWriter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

public class ExportKey {

	public static void main(String[] args) {

		try {
			
			// Cr�ation de la cl� AES
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			Key key = kg.generateKey();
			System.out.println("Key used -> " + key.toString());
			
			// Export de la cl� vers le fichier mykey
			PrintWriter writer = new PrintWriter("mykey.txt", "UTF-8");
			writer.println(key);
			writer.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
