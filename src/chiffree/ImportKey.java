package chiffree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ImportKey {
	
	String key;
	
	public ImportKey() {
		
		try {
			//On recupere la cle dans le fichier
			Scanner scan = new Scanner(new File("mykey.txt"));
			key = scan.next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	// Methode permettant de recuperer la cle dans le bon format
	public SecretKey getKey() throws UnsupportedEncodingException{
		return new SecretKeySpec(this.key.getBytes("ISO-8859-2"),"AES");
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		ImportKey ik = new ImportKey();
		System.out.println(ik.getKey());
	}

}
