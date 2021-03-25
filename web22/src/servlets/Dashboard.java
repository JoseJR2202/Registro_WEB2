package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.Controlador;
import control.Controlador_dashboard;

/**
 * Servlet implementation class Dashboard
 */
@MultipartConfig()
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        System.out.println("dasboard "+session.getAttribute("usuario"));
        if(session.getAttribute("usuario")!=null) 
        	out.println(Controlador_dashboard.dash(session));
        else
        	out.println("{\"status\":\"500\"}");
        out.close();	
     }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		System.out.println(request.getParameter("correo")+ " funciono en el servlet");
		PrintWriter out=response.getWriter();
		
		//verificando cual boton lo trajo hacia aqui
		if(request.getParameter("accion").equals("envio")) {
			
			if(session.getAttribute("usuario")!=null&&Controlador.actualizar(request.getParameter("nombre"),
					request.getParameter("correo") , request.getParameter("nacimiento"),
					request.getParameter("EDAD"),request.getParameter("Ubicacion"),request.getParameter("pass"),
					request.getParameter("Descripcion"),request.getParameter("Estudio"),request.getParameter("Hobbie")))
			{
				session.setAttribute("pass",request.getParameter("pass") );
				out.println("{\"status\":\"200\"}");
			}else
				out.println("{\"status\":\"500\"}");
		}else {
			session.invalidate();
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", -1);
			out.println("{\"status\":\"200\"}");
		}
		out.close();	
	}

}
