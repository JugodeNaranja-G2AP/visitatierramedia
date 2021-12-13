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

@WebServlet("/admin/users/create.do")
public class CreateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 2792106066044749125L;
	private UserService userService;
	private AttractionTypesService attractionTypesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
		this.attractionTypesService = new AttractionTypesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Tipo> tipos = attractionTypesService.list();
		req.setAttribute("tipos", tipos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/users/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre").trim();
		String contrasenia = req.getParameter("password").trim();
		int presupuesto = Integer.parseInt(req.getParameter("presupuesto").trim());
		double tiempoDisponible = Double.parseDouble(req.getParameter("tiempo").trim());
		Tipo tipo = attractionTypesService.findByName((req.getParameter("tipo_atraccion")));
		String imagen = req.getParameter("imagen_usuario");

		Usuario usuario = userService.create(nombre, contrasenia, presupuesto, tiempoDisponible, tipo, imagen);

		if (usuario.isValid()) {

			req.setAttribute("flash", "El usuario fue creado exitosamente!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/users/create.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("usuario", usuario);
			List<Tipo> tipos = attractionTypesService.list();
			req.setAttribute("tipos", tipos);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/users/create.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
