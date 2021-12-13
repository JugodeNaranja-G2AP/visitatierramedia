package controller.admins;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import services.AdminService;

@WebServlet("/admin/delete.do")
public class DeleteAdminsServlet extends HttpServlet {

	private static final long serialVersionUID = -4497344838974553475L;
	AdminService adminService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.adminService = new AdminService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Admin adminUser = (Admin) req.getSession().getAttribute("admin");
		req.setAttribute("adminUser", adminUser);
		
		adminService.delete(id);
		List<Admin> admins = adminService.list();
		req.setAttribute("admins", admins);
		
		req.setAttribute("flash", "El admin fue eliminado exitosamente!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/data-table.jsp");
		dispatcher.forward(req, resp);		
	}

}
