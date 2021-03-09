package helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	public Hashing() {
		
	}
	
	@SuppressWarnings("null")
	static public String encriptar(String clave) {
		try {
			MessageDigest encriptador = MessageDigest.getInstance("SHA-512");
			
			//le paso la clave
			encriptador.update((clave).getBytes());
			
			//convierto la cadena aleatoria en una en hexadecimal
			byte cadena[]=encriptador.digest();
			
			BigInteger valor = new BigInteger(1, cadena); 
			  
            // Convert message digest into hex value 
            String clave_nueva = valor.toString(16); 
			System.out.println(clave_nueva.length());
			return clave_nueva;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
