package helpers;

public class Autentificacion {
	private static Conexion conectar=Conexion.getInstances();
	public Autentificacion() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static boolean Chequeo (String correo,String pass) {
		String nueva_clave=Hashing.encriptar(pass);
		if(conectar.dbStatement("Select * from registro where correo like '"+correo+"' and pass like  '"+nueva_clave+"' ")!=null)
			return true;
		else
			return false;
	}

}
