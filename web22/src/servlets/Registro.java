package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.Controlador;

/**
 * Servlet implementation class Registro
 */
@MultipartConfig()
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
    public Registro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		response.setContentType("application/json");
		System.out.println(request.getParameter("pass")+ " funciono en el servlet");
		PrintWriter out=response.getWriter();
		
		out.println(Controlador.registro(request.getParameter("nombre"),
				request.getParameter("correo") , request.getParameter("nacimiento"),
				request.getParameter("EDAD"),request.getParameter("Ubicacion"),request.getParameter("pass")));
		
	}

}
