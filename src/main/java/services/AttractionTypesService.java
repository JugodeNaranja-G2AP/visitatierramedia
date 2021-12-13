package services;

import java.util.List;

import model.Tipo;
import persistence.TipoDAO;
import persistence.commons.DAOFactory;

public class AttractionTypesService {

	public List<Tipo> list() {
		return DAOFactory.getTipoDAO().findAll();
	}
	
	public Tipo create(String nombre) {
		Tipo tipo = new Tipo(-1, nombre);
		
		if(tipo.isValid()) {
			TipoDAO tipoDAO = DAOFactory.getTipoDAO();
			tipoDAO.insert(tipo);
		}
		
		return tipo;
	}
	
	public Tipo update(int id, String nombre) {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		Tipo tipo = tipoDAO.find(id);
		
		tipo.setNombre(nombre);
		
		if(tipo.isValid()) {
			tipoDAO.update(tipo);
		}
		
		return tipo;
	}
	
	public void delete(int id) {
		Tipo tipo = new Tipo(id, null);
		
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		tipoDAO.delete(tipo);
	}
	
	public Tipo find(int id) {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		return tipoDAO.find(id);
	}

	public Tipo findByName(String nombre) {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		return tipoDAO.findByName(nombre);
	}
	
	public int count() {
		return DAOFactory.getTipoDAO().countAll();
	}
}
