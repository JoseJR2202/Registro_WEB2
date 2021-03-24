package control;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import helpers.Conexion;
import helpers.Hashing;

public class Controlador_dashboard {
	private static Conexion conectar=Conexion.getInstances();
	
	public Controlador_dashboard() {
		// TODO Auto-generated constructor stub
	}

	public static String dash(HttpSession session) {
        try {
        	String nueva_clave=Hashing.encriptar(session.getAttribute("pass").toString());
        	ArrayList<String> datos=conectar.dbStatement("Select * from registro where correo like '"+session.getAttribute("usuario").toString()+"' and pass like  '"+nueva_clave+"' ");
            if(datos.get(0).equals(session.getAttribute("usuario")))
            	return "{\"status\":\"200\",\"correo\":\""+datos.get(0)+"\","
            		+ "\"nombre\":\""+datos.get(1)+"\",\"nacimiento\":"+datos.get(2)+"\","
            		+  "\"edad\":"+datos.get(3)+"\",\"ubicacion\":"+datos.get(4)+"\","+
            		"\"pass\":\""+datos.get(5)+"\"}";
            else
            	return "{\"status\":\"500\"}";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return null;
        }
    }
}
