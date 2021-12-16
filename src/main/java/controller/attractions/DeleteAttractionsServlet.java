package controller.attractions;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import services.AttractionService;

@WebServlet("/admin/attractions/delete.do")
public class DeleteAttractionsServlet extends HttpServlet {

	private static final long serialVersionUID = 6544581010372339407L;
	AttractionService attractionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		attractionService.delete(id);
		
		List<Atraccion> atracciones = attractionService.list();
		req.setAttribute("atracciones", atracciones);
		
		req.setAttribute("flash", "El admin fue eliminado exitosamente!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attractions/data-table.jsp");
		dispatcher.forward(req, resp);
	}
}
