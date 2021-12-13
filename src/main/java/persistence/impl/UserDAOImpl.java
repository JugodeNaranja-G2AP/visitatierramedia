package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.TipoDAO;
import persistence.UserDAO;
import persistence.commons.*;
import model.Atraccion;
import model.Promocion;
import model.Tipo;
import model.Usuario;
import model.nullobjects.NullUser;
import tierramedia.Producto;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new ArrayList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUser(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS 'Usuarios_Totales' FROM usuarios";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("Usuarios_Totales");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Usuario usuario) {
		try {

			String sql = "INSERT INTO usuarios (nombre, contrasenia, dinero_disponible, tiempo_disponible, tipo_actividad_preferida_id, imagen) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getContrasenia());
			statement.setInt(3, usuario.getPresupuesto());
			statement.setDouble(4, usuario.getTiempoDisponible());
			statement.setInt(5, usuario.getTipoAtraccionPreferida().getId());
			statement.setString(6, usuario.getImagen());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {

			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Usuario usuario) {
		try {
			String sql = "UPDATE usuarios SET nombre = ?, contrasenia = ?, dinero_disponible = ?, tiempo_disponible = ?," +
						 "tipo_actividad_preferida_id = ?, imagen = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setString(2, usuario.getContrasenia());
			statement.setInt(3, usuario.getPresupuesto());
			statement.setDouble(4, usuario.getTiempoDisponible());
			statement.setInt(5, usuario.getTipoAtraccionPreferida().getId());
			statement.setString(6, usuario.getImagen());
			statement.setInt(7, usuario.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario usuario) {
		try {
			String sql = "DELETE FROM usuarios WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Usuario find(int userId) {
		try {
			String sql = "SELECT * FROM usuarios WHERE usuarios.id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = null;

			if (resultados.next()) {
				usuario = toUser(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public Usuario findByUsername(String username) {
		try {
			String sql = "SELECT * FROM usuarios WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario usuario = NullUser.build();

			if (resultados.next()) {
				usuario = toUser(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Producto> obtenerItinerario(Usuario usuario) {
		try {
			String sql = "SELECT usuario_id, group_concat(promo_id) AS 'Lista_de_promociones' , group_concat(atraccion_id) AS 'Lista_de_atracciones'\r\n"
					+ "FROM productos_comprados\r\n" + "WHERE usuario_id = ? \r\n" + "GROUP BY usuario_id";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, usuario.getId());
			ResultSet resultados = statement.executeQuery();

			List<Producto> productos = new ArrayList<Producto>();

			while (resultados.next()) {
				productos = toItinerario(resultados);
			}

			return productos;

		} catch (Exception e) {

			throw new MissingDataException(e);
		}
	}

	@Override
	public int registrarCompra(Usuario usuario, Producto productoComprado) {
		try {

			String sql = "INSERT INTO productos_comprados (usuario_id, promo_id, atraccion_id) VALUES (?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			if (productoComprado.esPromocion()) {
				statement.setInt(1, usuario.getId());
				statement.setInt(2, ((Promocion) productoComprado).getId());
				statement.setNull(3, 0);
			}
			if (!productoComprado.esPromocion()) {
				statement.setInt(1, usuario.getId());
				statement.setNull(2, 0);
				statement.setInt(3, ((Atraccion) productoComprado).getId());
			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {

			throw new MissingDataException(e);
		}
	}

	private Usuario toUser(ResultSet resultados) throws SQLException {
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();
		int id = resultados.getInt(1);
		String nombre = resultados.getString(2);
		String contrasenia = resultados.getString(3);
		int dinero_disponible = resultados.getInt(4);
		double tiempo_disponible = resultados.getDouble(5);
		Tipo tipo_actividad_preferida = tipoDAO.find(resultados.getInt(6));
		String imagen = resultados.getString(7);
		
		return new Usuario(id, nombre, contrasenia, dinero_disponible, tiempo_disponible, tipo_actividad_preferida, imagen);
	}

	private List<Producto> toItinerario(ResultSet resultados) throws SQLException {

		List<Producto> productosComprados = new ArrayList<Producto>();
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		List<Promocion> promociones = new ArrayList<Promocion>();
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

		if (resultados.getString(2) != null) {
			promociones = promocionDAO.obtenerPromociones(resultados.getString(2));

			for (Promocion promo : promociones) {
				productosComprados.add(promo);
			}
		}

		if (resultados.getString(3) != null) {
			atracciones = atraccionDAO.obtenerAtracciones(resultados.getString(3));

			for (Atraccion atraccion : atracciones) {
				productosComprados.add(atraccion);
			}
		}

		return productosComprados;
	}

}
