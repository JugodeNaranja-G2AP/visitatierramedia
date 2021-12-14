package services;

import java.util.ArrayList;
import java.util.Collections;
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
import tierramedia.OrdenadorDeProducto;
import tierramedia.Producto;

public class ProductService {
	
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	UserDAO userDAO = DAOFactory.getUserDAO();
	
	public List<Producto> sortList(Usuario usuario) {
		List<Atraccion> atracciones = atraccionDAO.findAll();
		List<Promocion> promociones = promocionDAO.findAll();
		List<Producto> productos = new ArrayList<Producto>();
		
		
		productos.addAll(promociones);
		productos.addAll(atracciones);
		
		Collections.sort(productos, new OrdenadorDeProducto(usuario.getTipoAtraccionPreferida()));
		
		return productos;
	}
	
	public Producto find(String nombre, int id) {
		Producto producto = null;
		if(nombre.equals("promocion")) {
			producto = DAOFactory.getPromocionDAO().find(id);
		}
		if(nombre.equals("atraccion")) {
			producto = DAOFactory.getAtraccionDAO().find(id);
		}
		return producto;
	}
	
	public Map<String, String> buy(int userId, int productId, String productoNombre) {
		Map<String, String> errors = new HashMap<String, String>();
		Usuario usuario = userDAO.find(userId);
		Producto producto = null;
		
		if(productoNombre.equals("atraccion")) {
			 producto = atraccionDAO.find(productId);
			
			if (!producto.hayCupo()) {
				errors.put("atracción", "No hay cupo disponible");
			}
			if (!usuario.puedePagarlo(producto)) {
				errors.put("usuario", "No tenés monedas suficientes");
			}
			if (!usuario.tieneTiempoDisponible(producto)) {
				errors.put("user", "No tienes tiempo suficiente");
			}
		}
		
		if(productoNombre.equals("promocion")) {
			producto = promocionDAO.find(productId);
			
			if (!producto.hayCupo()) {
				errors.put("promocion", "No hay cupo disponible");
			}
			if (!usuario.puedePagarlo(producto)) {
				errors.put("usuario", "No tenés monedas suficientes");
			}
			if (!usuario.tieneTiempoDisponible(producto)) {
				errors.put("usuario", "No tenés tiempo suficiente");
			}				
		} 
		
		
		if (errors.isEmpty()) {
			
			usuario.reservarProducto(producto);
			userDAO.update(usuario);
			userDAO.registrarCompra(usuario, producto);
			
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
