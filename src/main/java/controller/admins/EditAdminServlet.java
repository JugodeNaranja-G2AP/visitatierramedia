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

@WebServlet("/admin/edit.do")
public class EditAdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 8230474858679165324L;
	AdminService adminService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.adminService = new AdminService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		int id = Integer.parseInt(req.getParameter("id"));
		
		Admin admin = adminService.find(id);
		req.setAttribute("admin", admin);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/edit.jsp");
		dispatcher.forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		String nombre = req.getParameter("nombre").trim();
		String contrasenia = req.getParameter("password").trim();
		String correo = req.getParameter("correo").trim();
		
		Admin admin = adminService.update(id, nombre, contrasenia, correo);
		
		if(admin.isValid()) {
			req.setAttribute("flash", "El admin fue actualizado exitosamente!");
			req.setAttribute("admin", admin);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/admin/edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			
			req.setAttribute("admin", admin);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/edit.jsp");
			dispatcher.forward(req, resp);
		} 
	}

}
