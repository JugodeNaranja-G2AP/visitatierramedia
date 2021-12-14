package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.LoginUserService;
import services.UserService;

@WebServlet("/login")
public class LoginUserServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4386749961128800128L;
	private LoginUserService loginUserService;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginUserService = new LoginUserService();
		userService = new UserService();
	}

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Usuario usuario = loginUserService.login(username, password);

		if (!usuario.isNull()) {
			
			usuario = userService.setItinerary(usuario);
			req.getSession().setAttribute("usuario", usuario);
			
			resp.sendRedirect("index.jsp");

		} else {
			req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			dispatcher.forward(req, resp);

		}
	}
}
