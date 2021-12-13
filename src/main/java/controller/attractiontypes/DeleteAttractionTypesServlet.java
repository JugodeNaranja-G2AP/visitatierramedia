package controller.attractiontypes;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tipo;
import services.AttractionTypesService;

@WebServlet("/admin/attraction-types/delete.do")
public class DeleteAttractionTypesServlet extends HttpServlet {

	private static final long serialVersionUID = 5856839654799453250L;
	private AttractionTypesService attractionTypesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionTypesService = new AttractionTypesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		attractionTypesService.delete(id);
		List<Tipo> tiposDeAtraccion = attractionTypesService.list();
		req.setAttribute("tiposDeAtraccion", tiposDeAtraccion);
		
		req.setAttribute("flash", "El tipo de atracción fue eliminado exitosamente!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attraction-types/data-table.jsp");
		dispatcher.forward(req, resp);	
	}
}
