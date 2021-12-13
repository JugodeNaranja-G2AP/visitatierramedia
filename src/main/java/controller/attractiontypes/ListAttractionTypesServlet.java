package controller.attractiontypes;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tipo;
import services.AttractionTypesService;

@WebServlet("/admin/attraction-types/data-table.do")
public class ListAttractionTypesServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 8253051668637930380L;
	private AttractionTypesService attractionTypesService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionTypesService = new AttractionTypesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Tipo> tiposDeAtraccion = attractionTypesService.list();
		req.setAttribute("tiposDeAtraccion", tiposDeAtraccion);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attraction-types/data-table.jsp");
		dispatcher.forward(req, resp);
	}
}
