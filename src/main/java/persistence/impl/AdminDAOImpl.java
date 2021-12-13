package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.commons.*;
import model.Admin;
import model.nullobjects.NullAdmin;
import persistence.AdminDAO;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public List<Admin> findAll() {
		try {
			String sql = "SELECT * FROM admins";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Admin> admins = new ArrayList<Admin>();
			while (resultados.next()) {
				admins.add(toAdmin(resultados));
			}

			return admins;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS 'Admins_Totales' FROM admins";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("Admins_Totales");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Admin admin) {
		try {

			String sql = "INSERT INTO admins (nombre, contrasenia, correo) VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, admin.getNombre());
			statement.setString(2, admin.getContrasenia());
			statement.setString(3, admin.getCorreo());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {

			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Admin admin) {
		try {
			String sql = "UPDATE admins SET nombre = ?, contrasenia = ?, correo = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, admin.getNombre());
			statement.setString(2, admin.getContrasenia());
			statement.setString(3, admin.getCorreo());
			statement.setInt(4, admin.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Admin admin) {
		try {
			String sql = "DELETE FROM admins WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, admin.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Admin find(int id) {
		try {
			String sql = "SELECT * FROM admins WHERE admins.id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Admin admin = NullAdmin.build();

			if (resultados.next()) {
				admin = toAdmin(resultados);
			}

			return admin;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Admin toAdmin(ResultSet resultados) throws SQLException {
		int id = resultados.getInt(1);
		String nombre = resultados.getString(2);
		String contrasenia = resultados.getString(3);
		String correo = resultados.getString(4);
			
		return new Admin(id, nombre, contrasenia, correo);
	}

	@Override
	public Admin findByNameOrEmail(String parametro) {
		try {
			String sql = "SELECT * FROM admins WHERE nombre = ? OR correo = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, parametro);
			statement.setString(2, parametro);
			ResultSet resultados = statement.executeQuery();
			
			Admin admin = NullAdmin.build();
			
			if(resultados.next()) {
				admin = toAdmin(resultados);
			}
			
			return admin;
			
		} catch(Exception e) {
			throw new MissingDataException(e);
		}
	}

}
