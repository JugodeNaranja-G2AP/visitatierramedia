package persistence;

import java.util.List;

import model.Atraccion;
import persistence.commons.GenericDAO;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public abstract List<Atraccion> obtenerAtracciones(String atracciones_id);
	
	public abstract Atraccion findByName(String nombre);

}
