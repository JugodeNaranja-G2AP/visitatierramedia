package controller.promotions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;
import services.PromotionService;

@WebServlet("/admin/promotions/delete.do")
public class DeletePromotionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1981001928151773936L;
	PromotionService promotionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		promotionService.delete(id);
		
		List<Promocion> promociones = promotionService.list();
		req.setAttribute("promociones", promociones);
		
		req.setAttribute("flash", "La promoción fue eliminada exitosamente!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attractions/data-table.jsp");
		dispatcher.forward(req, resp);
	}
}
