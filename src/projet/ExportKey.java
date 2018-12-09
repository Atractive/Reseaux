package projet;

import java.io.PrintWriter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;

public class ExportKey {

	public static void main(String[] args) {

		try {
			
			// Création de la clé AES
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			Key key = kg.generateKey();
			System.out.println("Key used -> " + key.toString());
			
			// Export de la clé vers le fichier mykey
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
