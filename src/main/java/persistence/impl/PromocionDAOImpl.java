package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.commons.*;
import model.ClaseDePromo;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.PromoPorcentual;
import model.Promocion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.TipoDAO;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public List<Promocion> findAll() {
		try {
			String sql = "SELECT promociones.id, promociones.nombre, promociones.descripcion, tipo_de_promo.nombre_tipo_promo, promociones.tipo,\n"
					+ "group_concat(lista_atracciones_por_promo.atraccion_id) 'Lista_de_atracciones',\n"
					+ "promociones.atributo_de_promo\n"
					+ "FROM promociones\n"
					+ "JOIN lista_atracciones_por_promo ON lista_atracciones_por_promo.promo_id =  promociones.id \n"
					+ "JOIN tipo_de_promo ON tipo_de_promo.id = promociones.tipo_de_promo_id\n"
					+ "GROUP BY promociones.nombre\n"
					+ "ORDER BY promociones.id";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promos = new ArrayList<Promocion>();
			while (resultados.next()) {
				promos.add(toPromocion(resultados));
			}

			return promos;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MissingDataException(e);

		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS 'Total_Promociones' FROM promociones";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("Total_Promociones");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Promocion promocion) {
		// TODO: Falta agregar la lista de atracciones a su correspondiente tabla
		// Si agregamos la lista concatenando las " ? " 
		try {
			String sql = "INSERT INTO promociones (nombre, descripcion, tipo, tipo_de_promo_id, atributo_de_promo)"
					+ "VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			if (promocion.getClase().equals(ClaseDePromo.PROMO_ABSOLUTA)) {
				statement.setString(1, promocion.getNombre());
				statement.setString(2, promocion.getDescripcion());
				statement.setInt(3, promocion.getTipo().getId());
				statement.setInt(4, promocion.getClase().getId());
				statement.setInt(5, promocion.getCosto());
			}
			if (promocion.getClase().equals(ClaseDePromo.PROMO_PORCENTUAL)) {
				statement.setString(1, promocion.getNombre());
				statement.setString(2, promocion.getDescripcion());
				statement.setInt(3, promocion.getTipo().getId());
				statement.setInt(4, promocion.getClase().getId());
				statement.setInt(5, ((PromoPorcentual) promocion).getPorcentajeDescuento());
			}
			if (promocion.getClase().equals(ClaseDePromo.PROMOAXB)) {
				statement.setString(1, promocion.getNombre());
				statement.setString(2, promocion.getDescripcion());
				statement.setInt(3, promocion.getTipo().getId());
				statement.setInt(4, promocion.getClase().getId());
				statement.setInt(5, ((PromoAxB) promocion).getAtraccionGratis().getId());
			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion promocion) {
		// TODO: Falta implementar el update para la lista de atracciones de la promo
		try {
			String sql = "UPDATE promociones SET nombre = ?, descripcion = ?, tipo = ?, tipo_de_promo_id = ?, atributo_de_promo = ?, WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);

			if (promocion.getClase().equals(ClaseDePromo.PROMO_ABSOLUTA)) { // TODO: revisar que me daría getClase en un
																			// print.
				statement.setString(1, promocion.getNombre());
				statement.setString(2, promocion.getDescripcion());
				statement.setInt(3, promocion.getTipo().getId());
				statement.setInt(4, promocion.getClase().getId());
				statement.setInt(5, promocion.getCosto());
			}
			if (promocion.getClase().equals(ClaseDePromo.PROMO_PORCENTUAL)) {
				statement.setString(1, promocion.getNombre());
				statement.setString(2, promocion.getDescripcion());
				statement.setInt(3, promocion.getTipo().getId());
				statement.setInt(4, promocion.getClase().getId());
				statement.setInt(5, ((PromoPorcentual) promocion).getPorcentajeDescuento());
			}
			if (promocion.getClase().equals(ClaseDePromo.PROMOAXB)) {
				statement.setString(1, promocion.getNombre());
				statement.setString(2, promocion.getDescripcion());
				statement.setInt(3, promocion.getTipo().getId());
				statement.setInt(4, promocion.getClase().getId());
				statement.setInt(5, ((PromoAxB) promocion).getAtraccionGratis().getId());
			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM promociones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion find(int promocionId) {
		try {
			String sql = "SELECT promociones.id, promociones.nombre, promociones.descripcion, tipo_de_promo.nombre_tipo_promo,\n"
					+ "promociones.tipo, group_concat(lista_atracciones_por_promo.atraccion_id) 'Lista_de_atracciones',\n"
					+ "promociones.atributo_de_promo\n"
					+ "FROM lista_atracciones_por_promo\n"
					+ "JOIN promociones ON promociones.id = lista_atracciones_por_promo.promo_id\n"
					+ "JOIN tipo_de_promo ON tipo_de_promo.id = promociones.tipo_de_promo_id\n"
					+ "WHERE promociones.id = ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocionId);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Promocion> obtenerPromociones(String promociones_id) {
		List<Promocion> promociones = new ArrayList<Promocion>();
		String[] promociones_id_str = null;
		promociones_id_str = promociones_id.split(",");

		for (String promocion_id : promociones_id_str) {
			promociones.add(find(Integer.parseInt(promocion_id)));
		}

		return promociones;
	}

	private Promocion toPromocion(ResultSet resultados) throws SQLException {
		Promocion promo = null;
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		TipoDAO tipoDAO = DAOFactory.getTipoDAO();

		if (resultados.getString(4).equals("Promo_Absoluta")) {

			promo = new PromoAbsoluta(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
					ClaseDePromo.valueOf(resultados.getString(4).toUpperCase()), tipoDAO.find(resultados.getInt(5)),
					atraccionDAO.obtenerAtracciones(resultados.getString(6)),resultados.getInt(7));
		}

		if (resultados.getString(4).equals("PromoAXB")) {

			promo = new PromoAxB(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
					ClaseDePromo.valueOf(resultados.getString(4).toUpperCase()), tipoDAO.find(resultados.getInt(5)),
					atraccionDAO.obtenerAtracciones(resultados.getString(6)), atraccionDAO.find(resultados.getInt(7)));
		}

		if (resultados.getString(4).equals("Promo_Porcentual")) {

			promo = new PromoPorcentual(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
					ClaseDePromo.valueOf(resultados.getString(4).toUpperCase()), tipoDAO.find(resultados.getInt(5)),
					atraccionDAO.obtenerAtracciones(resultados.getString(6)), resultados.getInt(7));
		}
		return promo;
	}

}
