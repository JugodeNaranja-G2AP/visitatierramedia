package services;

import model.Usuario;
import model.nullobjects.NullUser;
import persistence.UserDAO;
import persistence.commons.DAOFactory;

public class LoginUserService implements GenericLogin<Usuario> {

	@Override
	public Usuario login(String parametro, String password) {
		UserDAO userDAO = DAOFactory.getUserDAO();
		Usuario usuario = userDAO.findByUsername(parametro);
		
		if(usuario.isNull() || !usuario.checkPassword(password)) {
			usuario = NullUser.build();
		}
		
		return usuario;
	}

}
