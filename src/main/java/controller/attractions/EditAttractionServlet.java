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
import model.Tipo;
import services.AttractionService;
import services.AttractionTypesService;

@WebServlet("/admin/attractions/edit.do")
public class EditAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 4835509830363821260L;
	private AttractionService attractionService;
	private AttractionTypesService attractionTypesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.attractionTypesService = new AttractionTypesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Atraccion atraccion = attractionService.find(id);
		req.setAttribute("atraccion", atraccion);
		
		List<Tipo> tipos = attractionTypesService.list();
		req.setAttribute("tipos", tipos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attractions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id").trim());
		String nombre = req.getParameter("nombre").trim();
		String descripcion = req.getParameter("descripcion").trim();
		int costo = Integer.parseInt(req.getParameter("costo").trim());
		double tiempo = Double.parseDouble(req.getParameter("tiempo").trim());
		int cupo = Integer.parseInt(req.getParameter("cupo").trim());
		Tipo tipo = attractionTypesService.findByName((req.getParameter("tipo_atraccion")));
		String imagen = req.getParameter("imagen").trim();

		Atraccion atraccion = attractionService.update(id, nombre, descripcion, costo, tiempo, cupo, tipo, imagen);
		
		if (atraccion.isValid()) {

			req.setAttribute("flash", "La atracción fue actualizada exitosamente!");
			List<Tipo> tipos = attractionTypesService.list();
			req.setAttribute("tipos", tipos);
			req.setAttribute("atraccion", atraccion);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			List<Tipo> tipos = attractionTypesService.list();
			req.setAttribute("tipos", tipos);
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
