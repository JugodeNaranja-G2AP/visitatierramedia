package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;


@WebServlet("/profile.do")
public class ProfileUserServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -7062052476947931871L;

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario  =  (Usuario) req.getSession().getAttribute("usuario");
		
		
		req.getSession().setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/profile.jsp");
		dispatcher.forward(req, resp);
		
	}
}
