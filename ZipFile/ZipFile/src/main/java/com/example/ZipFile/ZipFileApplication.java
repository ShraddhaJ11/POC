package com.example.ZipFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZipFileApplication {

	public static void main(String[] args) throws IOException {
		 List<String> srcFiles = Arrays.asList("C:/Users/shraddha/Downloads/catalogo.pdf", "C:/Users/shraddha/Downloads/constants.docx","C:/Users/shraddha/Downloads/Document.docx");
	        FileOutputStream fos = new FileOutputStream("C:/Users/shraddha/Downloads/multiCompressed.zip");
	        ZipOutputStream zipOut = new ZipOutputStream(fos);
	        for (String srcFile : srcFiles) {
	            File fileToZip = new File(srcFile);
	            FileInputStream fis = new FileInputStream(fileToZip);
	            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
	            zipOut.putNextEntry(zipEntry);
	 
	            byte[] bytes = new byte[1024];
	            int length;
	            while((length = fis.read(bytes)) >= 0) {
	                zipOut.write(bytes, 0, length);
	            }
	            fis.close();
	        }
	        zipOut.close();
	        fos.close();
	}
}
