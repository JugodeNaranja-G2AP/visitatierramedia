package controller.users;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.UserService;

@WebServlet("/admin/users/delete.do")
public class DeleteUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1758513436112811512L;
	UserService userService;	
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		userService.delete(id);
		
		List<Usuario> usuarios = userService.list();
		req.setAttribute("usuarios", usuarios);
		
		req.setAttribute("flash", "El usuario fue eliminado exitosamente!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/users/data-table.jsp");
		dispatcher.forward(req, resp);
	}
}
