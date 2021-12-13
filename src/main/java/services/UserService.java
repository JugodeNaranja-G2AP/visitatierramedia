package services;

import java.util.List;

import model.Tipo;
import model.Usuario;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import tierramedia.Producto;
import utils.Crypt;

public class UserService {

	public List<Usuario> list() {
		return DAOFactory.getUserDAO().findAll();
	}
	
	public Usuario create(String nombre, String contrasenia, int presupuesto, double tiempoDisponible, Tipo tipoDeAtraccionPreferida, String imagen) {
		Usuario usuario = new Usuario(-1, nombre, Crypt.hash(contrasenia), presupuesto, tiempoDisponible, tipoDeAtraccionPreferida, imagen);
		
		if(usuario.isValid()) {
			UserDAO userDAO = DAOFactory.getUserDAO();
			userDAO.insert(usuario);
		}
		
		return usuario;
	}
	
	public Usuario update(int id, String nombre, String contrasenia, int presupuesto, double tiempoDisponible, Tipo tipoDeAtraccionPreferida, String imagen) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		Usuario usuario = userDAO.find(id);
		
		String passwordHashed = Crypt.hash(contrasenia);
		
		usuario.setNombre(nombre);
		usuario.setContrasenia(passwordHashed);
		usuario.setPresupuesto(presupuesto);
		usuario.setTiempoDisponible(tiempoDisponible);
		usuario.setTipoAtraccionPreferida(tipoDeAtraccionPreferida);
		usuario.setImagen(imagen);
		
		if(usuario.isValid()) {
			userDAO.update(usuario);
		}
		
		return usuario;
	}
	
	public void delete(int id) {
		Usuario usuario = new Usuario(id, null, null, id, id, null, null);
		
		UserDAO userDAO = DAOFactory.getUserDAO();
		userDAO.delete(usuario);
	}
	
	public Usuario find(int id) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		return userDAO.find(id);
	}
	
	public List<Producto> retriveItinerary(Usuario usuario) {
		return DAOFactory.getUserDAO().obtenerItinerario(usuario);
	}
	
	public List<Usuario> setItineraries(List<Usuario> usuarios) {
		UserDAO userDAO = DAOFactory.getUserDAO();	
		
		for(Usuario usuario: usuarios) {
			usuario.setProductosComprados(userDAO.obtenerItinerario(usuario));
		}
		
		return usuarios;
	}
	
	public int count() {
		return DAOFactory.getUserDAO().countAll();
	}
}
