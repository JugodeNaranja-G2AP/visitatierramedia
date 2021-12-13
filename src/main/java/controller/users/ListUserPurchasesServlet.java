package controller.users;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.UserService;
import tierramedia.Producto;

@WebServlet("/purchased.do")
public class ListUserPurchasesServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = -2150060609733038453L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario  =  (Usuario) req.getSession().getAttribute("usuario");
		List<Producto> productos = userService.retriveItinerary(usuario);
		
		req.setAttribute("productos", productos);
		req.setAttribute("usuario", usuario);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/purchased.jsp");
		dispatcher.forward(req, resp);
	}
}
