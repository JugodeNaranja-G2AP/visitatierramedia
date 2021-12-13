package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

}
