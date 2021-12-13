package persistence;

import model.Tipo;
import persistence.commons.GenericDAO;

public interface TipoDAO extends GenericDAO<Tipo> {
	
	public abstract Tipo findByName(String nombre);
}
