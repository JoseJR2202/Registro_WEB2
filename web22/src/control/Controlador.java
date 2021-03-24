package control;

import helpers.Conexion;
import helpers.Hashing;

public class Controlador {
	private static Conexion conectar=Conexion.getInstances();
	
	public Controlador() {
		// TODO Auto-generated constructor stub
	}

	static public boolean registro(String nombre, String correo,String nacimiento, String EDAD, String ubicacion, String pass) {
		Object[] obj= {correo,nombre,nacimiento,Integer.parseInt(EDAD),ubicacion, Hashing.encriptar(pass)};
		System.out.println(pass+" funciono en el servlet");
		
		try {
			return conectar.dbPrepareStatement("update registro set correo= ?, nombre=?, nacimiento=?,"
					+ "EDAD=?, Ubicacion=?, pass=? where correo like '"+correo+"'", obj);
		}catch (Exception e) {
			return false;
		}
		
	}
}
