package filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class FrontEndAuthenticationFilter implements Filter {
	
	private HttpServletRequest httpRequest;
	 
    private static final String[] loginRequiredURLs = {
            "/profile", "/purchased", "/product-details", "/products"
    };

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		httpRequest = (HttpServletRequest) request;
		 
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
 
        if (path.startsWith("/admin/")) {
            chain.doFilter(request, response);
            return;
        }
 
        HttpSession session = httpRequest.getSession(false);
 
        boolean isLoggedIn = (session != null && session.getAttribute("usuario") != null);
 
        String loginURI = httpRequest.getContextPath() + "/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
 
        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // Si el usuario ya está logueado y quiere loguearse nuevamente
            // se hace un forward a la homepage
            httpRequest.getRequestDispatcher("/").forward(request, response);
 
        } else if (!isLoggedIn && isLoginRequired()) {
        	// el usuario no está logueado y quiere acceder a un recurso que requiere login se le pide autenticación
            // haciendo un forward a la pág. de Login
            String loginPage = "/login.jsp";
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
            dispatcher.forward(request, response);
        } else {
            // Para las otras págs. que no requieren autenticación
            // o bien si el usuario ya está logueado, se continúa en la cadena.
            chain.doFilter(request, response);
        }
		
	}
	
	private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString();
 
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }
 
        return false;
    }

}
