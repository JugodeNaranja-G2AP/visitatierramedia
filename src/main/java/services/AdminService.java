package services;

import java.util.List;

import model.Admin;
import persistence.AdminDAO;
import persistence.commons.DAOFactory;
import utils.Crypt;

public class AdminService {

	public List<Admin> list() {
		return DAOFactory.getAdminDAO().findAll();
	}

	public Admin create(String nombre, String contrasenia, String correo) {

		Admin admin = new Admin(-1, nombre, Crypt.hash(contrasenia), correo);

		if (admin.isValid()) {
			AdminDAO adminDAO = DAOFactory.getAdminDAO();
			adminDAO.insert(admin);
		}

		return admin;
	}

	public Admin update(int id, String nombre, String contrasenia, String correo) {

		AdminDAO adminDAO = DAOFactory.getAdminDAO();
		Admin admin = adminDAO.find(id);

		String passwordHashed = Crypt.hash(contrasenia);

		admin.setNombre(nombre);
		admin.setContrasenia(passwordHashed);
		admin.setCorreo(correo);

		if (admin.isValid()) {
			adminDAO.update(admin);
		}

		return admin;
	}

	public void delete(int id) {
		Admin admin = new Admin(id, null, null, null);

		AdminDAO adminDAO = DAOFactory.getAdminDAO();
		adminDAO.delete(admin);
	}

	public Admin find(int id) {
		AdminDAO adminDAO = DAOFactory.getAdminDAO();
		return adminDAO.find(id);
	}

}
