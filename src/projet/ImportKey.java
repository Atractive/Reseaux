package projet;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ImportKey {
	
	String key;
	
	public ImportKey() {
		
		try {
			Scanner scan = new Scanner(new File("mykey.txt"));
			key = scan.next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public SecretKey getKey(){
		byte[] decodedKey = Base64.getDecoder().decode(this.key);
		SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
		return originalKey;
	}

	public static void main(String[] args) {
		ImportKey ik = new ImportKey();
		System.out.println(ik.getKey());
	}

}
