package controller.session;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/logout")
public class LogoutAdminServlet extends HttpServlet {

	private static final long serialVersionUID = -5056727710606056056L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getSession().removeAttribute("admin");
    	resp.sendRedirect("/visitatierramedia/admin/login.jsp");
  		    	
    }

}
