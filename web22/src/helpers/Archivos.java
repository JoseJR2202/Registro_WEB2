package helpers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Archivos {
	public static void upload(InputStream input, FileOutputStream output) {
		int lectura = 0;
		final byte[] bytes = new byte[1024];
		
		try {
			while((lectura = input.read(bytes)) != -1) {
				output.write(bytes, 0, lectura);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}