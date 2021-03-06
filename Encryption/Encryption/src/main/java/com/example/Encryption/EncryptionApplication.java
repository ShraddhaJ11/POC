package com.example.Encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EncryptionApplication {
	
	static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile){
		 try {
		       Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		       Cipher cipher = Cipher.getInstance("AES");
		       cipher.init(cipherMode, secretKey);

		       FileInputStream inputStream = new FileInputStream(inputFile);
		       byte[] inputBytes = new byte[(int) inputFile.length()];
		       inputStream.read(inputBytes);

		       byte[] outputBytes = cipher.doFinal(inputBytes);

		       FileOutputStream outputStream = new FileOutputStream(outputFile);
		       outputStream.write(outputBytes);

		       inputStream.close();
		       outputStream.close();

		    } catch (NoSuchPaddingException | NoSuchAlgorithmException 
	                     | InvalidKeyException | BadPaddingException
		             | IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
	            }
	     }

	public static void main(String[] args) {
		String key = "This is a secret";
		File inputFile = new File("C:/Users/shraddha/Downloads/catalogo.pdf");
		File encryptedFile = new File("C:/Users/shraddha/Downloads/catalogo.encrypted");
		File decryptedFile = new File("C:/Users/shraddha/Downloads/decrypted-catalogo.pdf");
			
		try {
			EncryptionApplication.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
			EncryptionApplication.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
		     System.out.println("Success");
		 } catch (Exception ex) {
		     System.out.println(ex.getMessage());
	             ex.printStackTrace();
		 }
	     }
		
}
