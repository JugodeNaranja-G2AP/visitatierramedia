package controller.products;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.ProductService;
import services.UserService;
import tierramedia.Producto;

@WebServlet("/product-details/purchase.do")
public class BuyProductServlet extends HttpServlet {

	private static final long serialVersionUID = -985638694857672213L;
	private ProductService productService;
	private UserService userService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.productService = new ProductService();
		this.userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int productoId = Integer.parseInt(req.getParameter("id"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		String productoNombre = req.getParameter("name");
		
		Map<String, String> errors = productService.buy(usuario.getId(), productoId, productoNombre);
		userService.setItinerary(usuario);
		Producto producto = productService.find(productoNombre, productoId);
		
		
		if (errors.isEmpty()) {
			req.setAttribute("producto", producto);
			req.setAttribute("flash", "Tu compra se realizó exitosamente. ¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/product-details.do");
		dispatcher.forward(req, resp);
		

	}
}
