package controller.admins;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import services.AdminService;

@WebServlet("/admin/create.do")
public class CreateAdminServlet extends HttpServlet {

	private static final long serialVersionUID = -3621366858677780137L;
	private AdminService adminService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.adminService = new AdminService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/create.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		String nombre = req.getParameter("nombre").trim();
		String contrasenia = req.getParameter("password").trim();
		String correo = req.getParameter("correo").trim();
		
		Admin admin = adminService.create(nombre, contrasenia, correo);
		if(admin.isValid()) {
			
			req.setAttribute("flash", "El admin fue creado exitosamente!");
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/create.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("admin", admin);
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/create.jsp");
			dispatcher.forward(req, resp);
		}
		
	}
	
	
}
