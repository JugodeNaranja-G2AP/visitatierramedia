package controller.admins;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Tipo;
import services.AttractionService;
import services.AttractionTypesService;
import services.PromotionService;
import services.UserService;

@WebServlet("/admin/")
public class AdminIndexServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 3831207609161988149L;
	private AttractionService attractionService;
	private AttractionTypesService attractionTypesService;
	private UserService userService;
	private PromotionService promotionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.attractionTypesService = new AttractionTypesService();
		this.userService = new UserService();
		this.promotionService = new PromotionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atracciones = attractionService.list();
		List<Tipo> tipos = attractionTypesService.list();
		int usuarios = userService.count();
		int promociones = promotionService.count();
		
		req.setAttribute("atracciones", atracciones);
		req.setAttribute("tipos", tipos);
		req.setAttribute("usuarios", usuarios);
		req.setAttribute("promociones", promociones);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/index.jsp");
		dispatcher.forward(req, resp);
	}
	

}
