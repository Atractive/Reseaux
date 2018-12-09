package projet;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class TestAES {

	public static void main(String[] args) {

		byte[] data;
		byte[] result;
		byte[] original;
		
		try {
			
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			Key key = kg.generateKey();
			Cipher cipher = Cipher.getInstance("AES");
			
			cipher.init(Cipher.ENCRYPT_MODE,key);
			data = "Hello World !".getBytes();
			result = cipher.doFinal(data);
			
			cipher.init(Cipher.DECRYPT_MODE,key);
			original = cipher.doFinal(result);
			System.out.println("Decrypted data : " + new String(original));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
