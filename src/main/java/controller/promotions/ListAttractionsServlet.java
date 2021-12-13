package controller.promotions;

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
import model.Promocion;
import services.PromotionService;

@WebServlet("/admin/promotions/listattractions.do")
public class ListAttractionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8102719430100544044L;
	private PromotionService promotionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Promocion promocion = promotionService.find(id);
		List<Atraccion> atracciones = promocion.getAtracciones();
		
		req.setAttribute("atracciones", atracciones);
		req.setAttribute("promocion", promocion);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/listattractions.jsp");
		dispatcher.forward(req, resp);
		
	}
}
