package control;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import helpers.Autentificacion;
import helpers.Conexion;
import helpers.Hashing;
public class Controlador_Login {
	private static Conexion conectar=Conexion.getInstances();

	public Controlador_Login() {
		// TODO Auto-generated constructor stub
	}
	
	 public static boolean login(HttpServletRequest request) {
	        try {
	        	String nueva_clave=Hashing.encriptar(request.getParameter("pass"));
	        	ArrayList<String> datos=conectar.dbStatement("Select * from registro where correo like '"+request.getParameter("correo")+"' and pass like  '"+nueva_clave+"' ");
	            if( datos.get(0).equals(request.getParameter("correo"))) {
	                HttpSession session = request.getSession();
	                session.setAttribute("usuario", request.getParameter("correo"));
	                return true;
	            } else {
	                return false;
	            }
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            return false;
	        }
	    }
}
