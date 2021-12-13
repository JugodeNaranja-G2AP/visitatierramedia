package controller.products;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.BuyProductService;

@WebServlet("/product-details/purchase.do")
public class BuyProductServlet extends HttpServlet {

	private static final long serialVersionUID = -985638694857672213L;
	private BuyProductService buyProductService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.buyProductService = new BuyProductService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int productoId = Integer.parseInt(req.getParameter("id"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("user");
		
		
		
//		Map<String, String> errors = buyProductService.buy(productoId, atraccionId, null)
	}
}
