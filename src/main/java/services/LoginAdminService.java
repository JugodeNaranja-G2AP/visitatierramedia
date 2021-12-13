package services;

import model.Admin;
import model.nullobjects.NullAdmin;
import persistence.AdminDAO;
import persistence.commons.DAOFactory;

public class LoginAdminService implements GenericLogin<Admin> {

	@Override
	public Admin login(String username, String password) {
		AdminDAO adminDAO = DAOFactory.getAdminDAO();
		Admin admin = adminDAO.findByNameOrEmail(username);
		
		if(admin.isNull() || !admin.checkPassword(password)) {
			admin = NullAdmin.build();
		}
		return admin;
	}

}
