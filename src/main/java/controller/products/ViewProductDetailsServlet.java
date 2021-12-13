package controller.products;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.ProductService;
import tierramedia.Producto;

@WebServlet("/product-details.do")
public class ViewProductDetailsServlet extends HttpServlet {

	private static final long serialVersionUID = -9195788588049296317L;
	private ProductService productService; 
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.productService = new ProductService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario =  (Usuario) req.getSession().getAttribute("usuario");	
		int productoId = Integer.parseInt(req.getParameter("id"));
		String productoNombre = req.getParameter("name");
		
		Producto producto = productService.find(productoNombre, productoId);
		
		req.setAttribute("producto", producto);
		req.setAttribute("usuario", usuario);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/product-details.jsp");
		 dispatcher.forward(req, resp);
		
	}
}
