package controller.promotions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.ClaseDePromo;
import model.Promocion;
import model.Tipo;
import services.AttractionService;
import services.AttractionTypesService;
import services.PromotionService;

@WebServlet("/admin/promotions/create.do")
public class CreatePromotionsServlet extends HttpServlet {

	private static final long serialVersionUID = -648743339356886234L;
	private AttractionService attractionService;
	private AttractionTypesService attractionTypesService;
	private PromotionService promotionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AttractionService();
		this.attractionTypesService = new AttractionTypesService();
		this.promotionService = new PromotionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Tipo> tipos = attractionTypesService.list();
		List<Atraccion> atracciones = attractionService.list();
		req.setAttribute("tipos", tipos);
		req.setAttribute("atracciones", atracciones);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/create.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Atraccion> atraccionesDeLaPromo = new ArrayList<Atraccion>();
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		ClaseDePromo claseDePromo = ClaseDePromo.valueOf(req.getParameter("clase_de_promo").toUpperCase());
		Tipo tipo = attractionTypesService.findByName(req.getParameter("tipo"));
		String[] atraccionesDePromo = req.getParameterValues("lista_de_atracciones");
		
		if(!atraccionesDePromo.equals(null)) {
			for(String atraccion: atraccionesDePromo) {
				atraccionesDeLaPromo.add(attractionService.findByName(atraccion));
			}
		}
		
		Promocion promocion = null;
		
		if(claseDePromo.equals(ClaseDePromo.PROMO_ABSOLUTA)) {
			int costoAbsoluto = Integer.parseInt(req.getParameter("costo_absoluto"));
			promocion = promotionService.createAbsoluta(nombre, descripcion, claseDePromo, tipo, atraccionesDeLaPromo, costoAbsoluto);		
			
		}
		if(claseDePromo.equals(ClaseDePromo.PROMOAXB)) {
			Atraccion atraccionGratis = attractionService.findByName(req.getParameter("atraccion_gratis"));
			promocion = promotionService.createAxB(nombre, descripcion, claseDePromo, tipo, atraccionesDeLaPromo, atraccionGratis);
		}
		if(claseDePromo.equals(ClaseDePromo.PROMO_PORCENTUAL)) {
			int descuento = Integer.parseInt(req.getParameter("porcentaje_descuento"));
			promocion = promotionService.createPorcentual(nombre, descripcion, claseDePromo, tipo, atraccionesDeLaPromo, descuento);
		}
		
		List<Tipo> tipos = attractionTypesService.list();
		List<Atraccion> atracciones = attractionService.list();
		
		if(promocion.isValid()) {
			
			req.setAttribute("tipos", tipos);
			req.setAttribute("atracciones", atracciones);
			
			req.setAttribute("flash", "La atracción fue creada exitosamente!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/create.jsp");
			dispatcher.forward(req, resp);
		} else {
			
			req.setAttribute("tipos", tipos);
			req.setAttribute("atracciones", atracciones);
			req.setAttribute("promocion", promocion);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/create.jsp");
			dispatcher.forward(req, resp);
			
		}
		
	}
}
