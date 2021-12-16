package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.commons.*;
import model.Atraccion;
import model.Tipo;
import persistence.AtraccionDAO;
import persistence.TipoDAO;

public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS 'Total_Atracciones' FROM atracciones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("Total_Atracciones");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO atracciones (nombre, descripcion, precio, tiempo, cupo, tipo_id, imagen) VALUES (?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setString(2, atraccion.getDescripcion());
			statement.setInt(3, atraccion.getCosto());
			statement.setDouble(4, atraccion.getTiempo());
			statement.setInt(5, atraccion.getCupoDePersonas());
			statement.setInt(6, atraccion.getTipo().getId());
			statement.setString(7, atraccion.getImagen());
			int rows = statement.executeUpdate();

			return rows;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion atraccion) {
		try {
			String sql = "UPDATE atracciones SET nombre = ?, descripcion = ?, precio = ?, tiempo = ?, cupo = ?, tipo_id = ?, imagen = ? WHERE id = ?";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setString(i++, atraccion.getNombre());
			statement.setString(i++, atraccion.getDescripcion());
			statement.setInt(i++, atraccion.getCostoDeVisita());
			statement.setDouble(i++, atraccion.getTiempoDeVisita());
			statement.setInt(i++, atraccion.getCupoDePersonas());
			statement.setInt(i++, atraccion.getTipo().getId());
			statement.setString(i++, atraccion.getImagen());
			statement.setInt(i++, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion atraccion) {
		try {
			String sql = "DELETE FROM atracciones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion find(int atraccionId) {
		try {
			String sql = "SELECT * FROM atracciones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccionId);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Atraccion> obtenerAtracciones(String atracciones_id) {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		String[] atracciones_id_str = null;
		atracciones_id_str = atracciones_id.split(",");
		for (String atraccion_id : atracciones_id_str) {
			atracciones.add(find(Integer.parseInt(atraccion_id)));
		}

		return atracciones;
	}

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();

		int id = resultados.getInt(1);
		String nombre = resultados.getString(2);
		String descripcion = resultados.getString(3);
		int costoDeVisita = resultados.getInt(4);
		double tiempoDeVisita = resultados.getDouble(5);
		int cupoDePersonas = resultados.getInt(6);
		Tipo tipo = tipoDAO.find(resultados.getInt(7));
		String imagen = resultados.getString(8);

		return new Atraccion(id, nombre, descripcion, costoDeVisita, tiempoDeVisita, cupoDePersonas, tipo, imagen);
	}

	@Override
	public Atraccion findByName(String nombre) {
		try {
			String sql = "SELECT * FROM atracciones WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
