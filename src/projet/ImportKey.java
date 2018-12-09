package projet;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.Scanner;

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
	
	public String getKey(){
		return this.key;
	}

	public static void main(String[] args) {
		ImportKey ik = new ImportKey();
		System.out.println(ik.getKey());
	}

}
