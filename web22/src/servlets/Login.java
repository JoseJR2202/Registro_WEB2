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

import control.Controlador_Login;

/**
 * Servlet implementation class Login
 */
@MultipartConfig()
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
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
		// TODO Auto-generated method stub
		response.addHeader("Access-Control-Allow-Origin","*");
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        if(Controlador_Login.login(request)) {
        	HttpSession session = request.getSession();
            String usuario=request.getParameter("correo");
            String pass=request.getParameter("pass");
            session.setAttribute("usuario", usuario);
            session.setAttribute("pass", pass);
            session.setMaxInactiveInterval(2);
        	out.println("{\"status\":\"200\"}");
        	 
        }
        else
        	out.println("{\"status\":\"500\"}");
        out.close();
	}

}
