package control;

import helpers.Conexion;
import helpers.Hashing;

public class Controlador {
	private static Conexion conectar=Conexion.getInstances();
	
	public Controlador() {
		// TODO Auto-generated constructor stub
	}

	static public String registro(String nombre, String correo,String nacimiento, String EDAD, String ubicacion, String pass) {
		Object[] obj= {correo,nombre,nacimiento,EDAD,ubicacion, Hashing.encriptar(pass)};
		System.out.println(pass+" funciono en el servlet");

		try {
			conectar.dbPrepareStatement("insert into registro (correo, nombre, nacimiento,"
					+ "EDAD, Ubicacion, pass) values( ?, ?, ?, ?, ?, ? )", obj);
			return "{\"message\":\"user creado\",\"status\":200}";
		}catch (Exception e) {
			return "{\"message\":\"user no creado\",\"status\":503}";
		}
		
	}
}
