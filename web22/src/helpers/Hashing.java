package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	public Hashing() {
		
	}
	
	@SuppressWarnings("null")
	static public String encriptar(String clave) {
		try {
			StringBuffer clave_nueva=new StringBuffer();
			MessageDigest encriptador = MessageDigest.getInstance("MD5");
			
			//le paso la clave
			encriptador.update(clave.getBytes());
			
			//convierto la cadena aleatoria en una en hexadecimal
			byte cadena[]=encriptador.digest();
			
			for(byte digito: cadena)
				clave_nueva.append(Integer.toHexString(0xFF & digito));
			return clave_nueva.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
