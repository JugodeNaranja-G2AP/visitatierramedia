package controller.admins;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import services.AdminService;

@WebServlet("/admin/data-table.do")
public class ListAdminsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -7367685815753993755L;
	private AdminService adminService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.adminService = new AdminService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Admin> admins = adminService.list();
		req.setAttribute("admins", admins);
		
		Admin adminUser = (Admin) req.getSession().getAttribute("admin");
		req.setAttribute("adminUser", adminUser);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/data-table.jsp");
		dispatcher.forward(req, resp);
	}

}
