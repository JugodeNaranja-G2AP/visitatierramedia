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

@WebServlet("/admin/promotions/edit.do")
public class EditPromotionServlet extends HttpServlet {

	static final long serialVersionUID = 6876994776318425329L;
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
		int id = Integer.parseInt(req.getParameter("id"));
		Promocion promocion = promotionService.find(id);

		List<Tipo> tipos = attractionTypesService.list();
		req.setAttribute("tipos", tipos);

		List<Atraccion> atracciones = attractionService.list();
		req.setAttribute("atracciones", atracciones);

		req.setAttribute("promocion", promocion);
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id").trim());
		List<Atraccion> atraccionesDeLaPromo = new ArrayList<Atraccion>();
		String nombre = req.getParameter("nombre").trim();
		String descripcion = req.getParameter("descripcion").trim();
		ClaseDePromo claseDePromo = ClaseDePromo.valueOf(req.getParameter("clase_de_promo").toUpperCase());
		Tipo tipo = attractionTypesService.findByName(req.getParameter("tipo"));
		String[] atraccionesDePromo = req.getParameterValues("lista_de_atracciones");

		if (!atraccionesDePromo.equals(null)) {
			for (String atraccion : atraccionesDePromo) {
				atraccionesDeLaPromo.add(attractionService.findByName(atraccion));
			}
		}

		Promocion promocion = null;

		if (claseDePromo.equals(ClaseDePromo.PROMO_ABSOLUTA)) {
			int costoAbsoluto = Integer.parseInt(req.getParameter("costo_absoluto"));
			promocion = promotionService.updateAbsoluta(id, nombre, descripcion, claseDePromo, tipo,
					atraccionesDeLaPromo, costoAbsoluto);

		}
		if (claseDePromo.equals(ClaseDePromo.PROMOAXB)) {
			Atraccion atraccionGratis = attractionService.findByName(req.getParameter("atraccion_gratis"));
			promocion = promotionService.updateAxB(id, nombre, descripcion, claseDePromo, tipo, atraccionesDeLaPromo,
					atraccionGratis);
		}
		if (claseDePromo.equals(ClaseDePromo.PROMO_PORCENTUAL)) {
			int descuento = Integer.parseInt(req.getParameter("porcentaje_descuento"));
			promocion = promotionService.updatePorcentual(id, nombre, descripcion, claseDePromo, tipo,
					atraccionesDeLaPromo, descuento);
		}

		List<Tipo> tipos = attractionTypesService.list();
		List<Atraccion> atracciones = attractionService.list();

		if (promocion.isValid()) {

			req.setAttribute("tipos", tipos);
			req.setAttribute("atracciones", atracciones);
			req.setAttribute("promocion", promocion);

			req.setAttribute("flash", "La atracción fue editada exitosamente!");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/edit.jsp");
			dispatcher.forward(req, resp);
		} else {

			req.setAttribute("tipos", tipos);
			req.setAttribute("atracciones", atracciones);
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/promotions/edit.jsp");
			dispatcher.forward(req, resp);

		}
	}
}
