package services;

import java.util.List;

import model.Atraccion;
import model.Tipo;
import persistence.AtraccionDAO;
import persistence.commons.DAOFactory;

public class AttractionService {
	
	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().findAll();
	}

	public Atraccion create(String nombre, String descripcion, int costoDeVisita, double tiempoDeVisita, int cupoDePersonas, Tipo tipo, String imagen) {

		Atraccion atraccion = new Atraccion(-1, nombre, descripcion, costoDeVisita, tiempoDeVisita, cupoDePersonas, tipo, imagen);

		if (atraccion.isValid()) {
			AtraccionDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(atraccion);
			
		}

		return atraccion;
	}

	public Atraccion update(int id, String nombre, String descripcion, int costoDeVisita, double tiempoDeVisita, int cupoDePersonas, Tipo tipo, String imagen) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.find(id);

		atraccion.setNombre(nombre);
		atraccion.setDescripcion(descripcion);
		atraccion.setCostoDeVisita(costoDeVisita);
		atraccion.setTiempoDeVisita(tiempoDeVisita);
		atraccion.setCupoDePersonas(cupoDePersonas);
		atraccion.setTipo(tipo);
		atraccion.setImagen(imagen);
		
		

		if (atraccion.isValid()) {
			atraccionDAO.update(atraccion);		
		}
		
		return atraccion;
	}

	public void delete(int id) {
		Atraccion atraccion = new Atraccion(null, null, 0, 0, 0, null, null);

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.delete(atraccion);
	}

	public Atraccion find(Integer id) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.find(id);
	}
	
	public Atraccion findByName(String nombre) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.findByName(nombre);
	}
	
	public int count() {
		return DAOFactory.getAtraccionDAO().countAll();
	}

}
