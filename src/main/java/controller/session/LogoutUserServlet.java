package controller.session;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutUserServlet extends HttpServlet {

	private static final long serialVersionUID = 2880283925128579779L;
	
	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.getSession().removeAttribute("usuario");
    	resp.sendRedirect("/visitatierramedia/index.jsp");
  		    	
    }

}
