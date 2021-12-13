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

@WebServlet("/admin/attractions/create.do")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = -5597820419879682831L;
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
		List<Tipo> tipos = attractionTypesService.list();
		req.setAttribute("tipos", tipos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attractions/create.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre").trim();
		String descripcion = req.getParameter("descripcion").trim();
		int costo = Integer.parseInt(req.getParameter("costo").trim());
		double tiempo = Double.parseDouble(req.getParameter("tiempo").trim());
		int cupo = Integer.parseInt(req.getParameter("cupo").trim());
		Tipo tipo = attractionTypesService.findByName(req.getParameter("tipo_atraccion"));
		String imagen = req.getParameter("imagen").trim();
		
		Atraccion atraccion = attractionService.create(nombre, descripcion, costo, tiempo, cupo, tipo, imagen);
		
		if(atraccion.isValid()) {
			
			req.setAttribute("flash", "La atracción fue creada exitosamente!");
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/attractions/create.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("atraccion", atraccion);
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/attractions/create.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
