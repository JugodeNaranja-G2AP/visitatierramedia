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

@WebServlet("/admin/users/itinerary.do")
public class ListUserItineraryServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 3493435588821580409L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Usuario usuario = userService.find(id);
		List<Producto> productos = userService.retriveItinerary(usuario);
		
		req.setAttribute("productos", productos);
		req.setAttribute("usuario", usuario);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/users/itinerary.jsp");
		dispatcher.forward(req, resp);
	}

}
