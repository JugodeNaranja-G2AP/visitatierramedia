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

@WebServlet("/admin/attraction-types/create.do")
public class CreateAttractionTypesServlet extends HttpServlet {

	private static final long serialVersionUID = 8430409523232554690L;
	private AttractionTypesService attractionTypesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionTypesService = new AttractionTypesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/attraction-types/create.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		String nombre = req.getParameter("nombre").trim();
		
		Tipo tipo = attractionTypesService.create(nombre);
		if(tipo.isValid()) {
			req.setAttribute("flash", "El tipo de atracción fue creado exitosamente!");
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/attraction-types/create.jsp");
			dispatcher.forward(req, resp);
			
		} else {
			req.setAttribute("tipo", tipo);
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/attraction-types/create.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
