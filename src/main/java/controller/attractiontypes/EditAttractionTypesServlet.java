package controller.attractiontypes;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tipo;
import services.AttractionTypesService;

@WebServlet("/admin/attraction-types/edit.do")
public class EditAttractionTypesServlet extends HttpServlet {

	private static final long serialVersionUID = 7798273888847661156L;
	private AttractionTypesService attractionTypesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionTypesService = new AttractionTypesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		Tipo tipo = attractionTypesService.find(id);
		req.setAttribute("tipo", tipo);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attraction-types/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		String nombre = req.getParameter("nombre").trim();
		
		Tipo tipo = attractionTypesService.update(id, nombre);
		
		if(tipo.isValid()) {
			req.setAttribute("flash", "El tipo de atracción fue actualizado exitosamente!");
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/attraction-types/edit.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			req.setAttribute("tipo", tipo);
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/attraction-types/edit.jsp");
			dispatcher.forward(req, resp);
		}
		
	}
}
