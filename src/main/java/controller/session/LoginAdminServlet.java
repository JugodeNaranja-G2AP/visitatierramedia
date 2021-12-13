package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Admin;
import services.LoginAdminService;

@WebServlet("/admin/login")
public class LoginAdminServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = 6875664113102822306L;
	private LoginAdminService loginAdminService;

	@Override
	public void init() throws ServletException { 
		super.init();
		loginAdminService = new LoginAdminService();

	}

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
    	String password = req.getParameter("password");
    	
    	Admin admin = loginAdminService.login(username, password);

    	
    	if(!admin.isNull()) {
    		req.getSession().setAttribute("admin", admin);
    		resp.sendRedirect("/visitatierramedia/admin/"); 
    	} else {
    		req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");
    		
    		RequestDispatcher dispatcher = getServletContext()
      		      .getRequestDispatcher("/admin/login.jsp");
      		    dispatcher.forward(req, resp);
    	}
    	
	}

}
