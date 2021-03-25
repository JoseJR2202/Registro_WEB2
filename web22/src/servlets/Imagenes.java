  
package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import helpers.Archivos;

/**
 * Servlet implementation class Imagenes
 */
@MultipartConfig()
@WebServlet("/Imagenes")
public class Imagenes extends HttpServlet {
	private static final long serialVersionUID = 1L;  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		
		Part filePart = request.getPart("foto"); 
		System.out.println("Archivo recibido:"+request.getParameter("nombre")+" -de valor:");
		InputStream fileContent = filePart.getInputStream();
		
		HttpSession sesion = request.getSession();
		
		File carpeta = new File("files"+"/"+
								sesion.getAttribute("usuario")+"/"+
								request.getParameter("nombre"));
		
		if(carpeta.mkdirs()) {
			System.out.println("Carpeta del usuario:"+sesion.getAttribute("usuario")+" creada exitosamente");
		}
		
		System.out.println("Direccion del archivo:"+carpeta.getAbsolutePath()+"/"+request.getParameter("nombre"));
		
		FileOutputStream output = new FileOutputStream(carpeta.getAbsolutePath()+"/"+request.getParameter("nombre"));
		
		Archivos.upload(fileContent, output);
	}
}