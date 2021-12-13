package services;

import java.util.List;

import model.Atraccion;
import model.ClaseDePromo;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.PromoPorcentual;
import model.Promocion;
import model.Tipo;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromotionService {
	
	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}
	
	public Promocion create(String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones, String imagen) {
		return null;
	}
	
	public int count() {
		return DAOFactory.getPromocionDAO().countAll();
	}
	
	public Promocion find(int id) {
		return DAOFactory.getPromocionDAO().find(id);
	}
	
	public void delete(int id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);
		if(promocion.getClase().equals(ClaseDePromo.PROMOAXB)) {
			promocion = new PromoAxB(id, null, null, null, null, null, null);
		}
		if(promocion.getClase().equals(ClaseDePromo.PROMO_ABSOLUTA)) {
			promocion = new PromoAbsoluta(id, null, null, null, null, null, id);
		}
		if(promocion.getClase().equals(ClaseDePromo.PROMO_PORCENTUAL)) {
			promocion = new PromoPorcentual(id, null, null, null, null, null, id); 
		}
		
		promocionDAO.delete(promocion);
	}

}
