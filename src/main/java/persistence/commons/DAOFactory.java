package persistence.commons;

import persistence.AdminDAO;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.TipoDAO;
import persistence.UserDAO;
import persistence.impl.AdminDAOImpl;
import persistence.impl.AtraccionDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.TipoDAOImpl;
import persistence.impl.UserDAOImpl;

public class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}

	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
	
	public static TipoDAO getTipoDAO() {
		return new TipoDAOImpl();
	}
	
	public static AdminDAO getAdminDAO() {
		return new AdminDAOImpl();
	}
}
