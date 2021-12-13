package persistence;

import model.Admin;
import persistence.commons.GenericDAO;

public interface AdminDAO extends GenericDAO<Admin> {

	public abstract Admin findByNameOrEmail(String parametro);
}
