package persistence;

import java.util.List;

import model.Atraccion;
import model.Promocion;
import persistence.commons.GenericDAO;

public interface PromocionDAO extends GenericDAO<Promocion> {

	public abstract List<Promocion> obtenerPromociones(String promociones_id);
	
	public abstract int findPromotionId(String nombre);
	
	public abstract int insertAttractionsList(int promoId, Atraccion atraccion);
	
	public abstract int deleteAttractionList(int promoId);
}
