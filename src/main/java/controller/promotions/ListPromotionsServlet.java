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
import services.AttractionService;
import services.PromotionService;

@WebServlet("/admin/promotions/data-table.do")
public class ListPromotionsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -5802010737075064383L;
	private PromotionService promotionService;
	private AttractionService attractionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
		this.attractionService = new AttractionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Promocion> promociones =  promotionService.list();
		List<Atraccion> atracciones = attractionService.list();
		
		req.setAttribute("atracciones", atracciones);			
		req.setAttribute("promociones", promociones);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/data-table.jsp");
		dispatcher.forward(req, resp);
	}

}
