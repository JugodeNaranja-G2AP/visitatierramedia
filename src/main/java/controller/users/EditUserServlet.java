package controller.users;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tipo;
import model.Usuario;
import services.AttractionTypesService;
import services.UserService;

@WebServlet("/admin/users/edit.do")
public class EditUserServlet extends HttpServlet {

	private static final long serialVersionUID = -2721174701174059225L;
	UserService userService;
	AttractionTypesService attractionTypesService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
		this.attractionTypesService = new AttractionTypesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Usuario usuario = userService.find(id);
		List<Tipo> tipos = attractionTypesService.list();
		req.setAttribute("tipos", tipos);
		req.setAttribute("usuario", usuario);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/users/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id").trim());	
		String nombre = req.getParameter("nombre").trim();
		String contrasenia = req.getParameter("password").trim();
		int presupuesto = Integer.parseInt(req.getParameter("presupuesto").trim());
		double tiempoDisponible = Double.parseDouble(req.getParameter("tiempo").trim());
		Tipo tipo = attractionTypesService.findByName((req.getParameter("tipo_atraccion")));
		String imagen = req.getParameter("imagen_usuario");
		
		Usuario usuario = userService.update(id, nombre, contrasenia, presupuesto, tiempoDisponible, tipo, imagen);
		
		if(usuario.isValid()) {		
			req.setAttribute("usuario", usuario);
			List<Tipo> tipos = attractionTypesService.list();
			req.setAttribute("tipos", tipos);	
			req.setAttribute("flash", "El usuario fue actualizado exitosamente!");
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/users/edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			
			req.setAttribute("usuario", usuario);
			List<Tipo> tipos = attractionTypesService.list();
			req.setAttribute("tipos", tipos);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/users/edit.jsp");
			dispatcher.forward(req, resp);
		} 
	}
	
}
