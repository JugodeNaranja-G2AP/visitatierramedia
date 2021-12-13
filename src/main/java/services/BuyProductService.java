package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Atraccion;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.UserDAO;
import persistence.commons.DAOFactory;
import tierramedia.Producto;


public class BuyProductService {
	
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();
	
	
	public Map<String, String> buy(int userId, int productId, Producto producto) {
		Map<String, String> errors = new HashMap<String, String>();
		Usuario usuario = userDAO.find(userId);
		
		if(producto.esPromocion()) {
			Promocion promocion = promocionDAO.find(productId);
			
			if (!promocion.hayCupo()) {
				errors.put("promocion", "No hay cupo disponible");
			}
			if (!usuario.puedePagarlo(producto)) {
				errors.put("usuario", "No tenés dinero suficiente");
			}
			if (!usuario.tieneTiempoDisponible(producto)) {
				errors.put("usuario", "No tenés tiempo suficiente");
			}				
		}
		
		if(!producto.esPromocion()) {
			Atraccion atraccion = atraccionDAO.find(productId);
			
			if (!atraccion.hayCupo()) {
				errors.put("atracción", "No hay cupo disponible");
			}
			if (!usuario.puedePagarlo(producto)) {
				errors.put("usuario", "No tenés dinero suficiente");
			}
			if (!usuario.tieneTiempoDisponible(producto)) {
				errors.put("user", "No tienes tiempo suficiente");
			}
		}
		
		if (errors.isEmpty()) {
			usuario.reservarProducto(producto);
			userDAO.update(usuario);
			
			if(producto.esPromocion()) {
				List<Atraccion> atracciones = producto.obtenerAtracciones();
				for(Atraccion atraccion: atracciones) {
					atraccionDAO.update(atraccion);
				}
			} else {
				 Atraccion atraccion = (Atraccion) producto;
				 atraccionDAO.update(atraccion);
			}		
		}
		
		return errors;
		
	}

}
