package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.commons.*;
import model.Tipo;
import persistence.TipoDAO;

public class TipoDAOImpl implements TipoDAO {

	@Override
	public List<Tipo> findAll() {
		try {
			String sql = "SELECT * FROM tipo_de_atracciones";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Tipo> tipos = new ArrayList<Tipo>();
			while (resultados.next()) {
				tipos.add(toTipo(resultados));
			}

			return tipos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS 'Total_Tipo_Atracciones' FROM tipo_de_atracciones";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("Total_Tipo_Atracciones");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Tipo tipo) {
		try {
			String sql = "INSERT INTO tipo_de_atracciones (tipo) VALUES (?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipo.getNombre());
			
			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Tipo tipo) {
		try {
			String sql = "UPDATE tipo_de_atracciones SET tipo = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tipo.getNombre());
			statement.setInt(2, tipo.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Tipo tipo) {
		try {
			String sql = "DELETE FROM tipo_de_atracciones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, tipo.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Tipo find(int tipoId) {
		try {
			String sql = "SELECT * FROM tipo_de_atracciones WHERE id = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, tipoId);
			ResultSet resultados = statement.executeQuery();

			Tipo tipo = null;

			if (resultados.next()) {
				tipo = toTipo(resultados);
			}

			return tipo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Tipo toTipo(ResultSet resultados) throws SQLException {
		int id = resultados.getInt(1);
		String tipo = resultados.getString(2);
		
		return new Tipo(id, tipo);
	}

	@Override
	public Tipo findByName(String nombre) {
		try {
			String sql = "SELECT * FROM tipo_de_atracciones WHERE tipo = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Tipo tipo = null;

			if (resultados.next()) {
				tipo = toTipo(resultados);
			}

			return tipo;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
