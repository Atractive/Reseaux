package chiffree;

import java.io.PrintWriter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class ExportKey {

	public static void main(String[] args) {

		try {
			
			// Création de la clé AES
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			SecretKey key = kg.generateKey();
			String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
			System.out.println("Key used -> " + encodedKey.toString());
			
			// Export de la clé vers le fichier mykey
			PrintWriter writer = new PrintWriter("mykey.txt", "UTF-8");
			writer.println(encodedKey);
			writer.close();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
