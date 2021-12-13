package persistence;

import java.util.List;

import model.Usuario;
import persistence.commons.GenericDAO;
import tierramedia.Producto;

public interface UserDAO extends GenericDAO<Usuario> {

	public abstract List<Producto> obtenerItinerario(Usuario usuario);

	public abstract int registrarCompra(Usuario usuario, Producto productoComprado);
	
	public abstract Usuario findByUsername(String username);
}
