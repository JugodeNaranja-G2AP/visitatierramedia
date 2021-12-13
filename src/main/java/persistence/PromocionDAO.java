package persistence;

import java.util.List;

import model.Promocion;
import persistence.commons.GenericDAO;

public interface PromocionDAO extends GenericDAO<Promocion> {

	public abstract List<Promocion> obtenerPromociones(String promociones_id);
}
