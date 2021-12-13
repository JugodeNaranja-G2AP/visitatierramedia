package controller.products;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.ProductService;
import tierramedia.Producto;

@WebServlet("/products.do")
public class ListProductsServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -6962389835062403005L;
	private ProductService productsService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.productsService = new ProductService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 Usuario usuario =  (Usuario) req.getSession().getAttribute("usuario");	 
		 List<Producto> productos = productsService.sortList(usuario);	 
		 req.setAttribute("productos", productos);
		 
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/products.jsp");
		 dispatcher.forward(req, resp);
		 
	}
}
