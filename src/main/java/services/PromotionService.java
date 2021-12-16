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

	public Promocion createAbsoluta(String nombre, String descripcion, ClaseDePromo clase, Tipo tipo,
			List<Atraccion> atracciones, int costoAbsolutoDePromo) {

		Promocion promocion = new PromoAbsoluta(nombre, descripcion, clase, tipo, atracciones, costoAbsolutoDePromo);

		if (((PromoAbsoluta) promocion).isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert((Promocion) promocion);
			int promoId = promocionDAO.findPromotionId(promocion.getNombre());
			for (Atraccion atraccion : atracciones) {
				promocionDAO.insertAttractionsList(promoId, atraccion);
			}
		}
		return promocion;
	}

	public Promocion createAxB(String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones, 
			Atraccion atraccionGratis) {
		Promocion promocion = new PromoAxB(nombre, descripcion, clase, tipo, atracciones, atraccionGratis);

		if (((PromoAxB) promocion).isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert((Promocion) promocion);
			int promoId = promocionDAO.findPromotionId(promocion.getNombre());
			for (Atraccion atraccion : atracciones) {
				promocionDAO.insertAttractionsList(promoId, atraccion);
			}
		}
		return promocion;
	}

	public Promocion createPorcentual(String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			int porcentajeDescuento) {
		Promocion promocion = new PromoPorcentual(nombre, descripcion, clase, tipo, atracciones, porcentajeDescuento);

		if (((PromoPorcentual) promocion).isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert((Promocion) promocion);
			int promoId = promocionDAO.findPromotionId(promocion.getNombre());
			for (Atraccion atraccion : atracciones) {
				promocionDAO.insertAttractionsList(promoId, atraccion);
			}
		}
		return promocion;
	}
	
	public Promocion updateAbsoluta(int id, String nombre, String descripcion, ClaseDePromo clase, Tipo tipo,
			List<Atraccion> atracciones, int costoAbsolutoDePromo) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);
		
		promocion.setNombre(nombre);
		promocion.setDescripcion(descripcion);
		promocion.setClase(clase);
		promocion.setTipo(tipo);
		promocion.setAtracciones(atracciones);
		((PromoAbsoluta) promocion).setCostoDePromo(costoAbsolutoDePromo);
		
		if(promocion.isValid()) {
			promocionDAO.update(promocion);
			promocionDAO.deleteAttractionList(promocion.getId());			
			for(Atraccion atraccion: atracciones) {
				promocionDAO.insertAttractionsList(promocion.getId(), atraccion);
			}
		}
		return promocion;
	}
	
	public Promocion updateAxB(int id, String nombre, String descripcion, ClaseDePromo clase, Tipo tipo,
			List<Atraccion> atracciones, Atraccion atraccionGratis) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);
		
		promocion.setNombre(nombre);
		promocion.setDescripcion(descripcion);
		promocion.setClase(clase);
		promocion.setTipo(tipo);
		promocion.setAtracciones(atracciones);
		((PromoAxB) promocion).setAtraccionGratis(atraccionGratis);
		
		if(promocion.isValid()) {
			promocionDAO.update(promocion);
			promocionDAO.deleteAttractionList(promocion.getId());			
			for(Atraccion atraccion: atracciones) {
				promocionDAO.insertAttractionsList(promocion.getId(), atraccion);
			}
		}
		return promocion;
	}
	
	public Promocion updatePorcentual(int id, String nombre, String descripcion, ClaseDePromo clase, Tipo tipo,
			List<Atraccion> atracciones, int porcentajeDescuento) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);
		
		promocion.setNombre(nombre);
		promocion.setDescripcion(descripcion);
		promocion.setClase(clase);
		promocion.setTipo(tipo);
		promocion.setAtracciones(atracciones);
		((PromoPorcentual) promocion).setPorcentajeDescuento(porcentajeDescuento);
		
		if(promocion.isValid()) {
			promocionDAO.update(promocion);
			promocionDAO.deleteAttractionList(promocion.getId());			
			for(Atraccion atraccion: atracciones) {
				promocionDAO.insertAttractionsList(promocion.getId(), atraccion);
			}
		}
		return promocion;
	}
	
	public Promocion updateAxB() {
		return null;
	}
	
	public Promocion updatePocentual() {
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
		if (promocion.getClase().equals(ClaseDePromo.PROMOAXB)) {
			promocion = new PromoAxB(id, null, null, null, null, null, null);
		}
		if (promocion.getClase().equals(ClaseDePromo.PROMO_ABSOLUTA)) {
			promocion = new PromoAbsoluta(id, null, null, null, null, null, id);
		}
		if (promocion.getClase().equals(ClaseDePromo.PROMO_PORCENTUAL)) {
			promocion = new PromoPorcentual(id, null, null, null, null, null, id);
		}

		promocionDAO.delete(promocion);
	}

}
