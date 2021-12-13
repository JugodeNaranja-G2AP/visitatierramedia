package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import tierramedia.Producto;
import utils.Crypt;
import utils.Reloj;

public class Usuario {

	private int id;
	private String nombre;
	private String contrasenia;
	private int presupuesto;
	private double tiempoDisponible;
	private Tipo tipoAtraccionPreferida;
	private String imagen;
	private List<Producto> productosComprados;
	private Map<String, String> errors;

	public Usuario(int id, String nombre, String contrasenia, int presupuesto, double tiempoDisponible,
			Tipo tipoDeAtraccionPreferida, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoDeAtraccionPreferida;
		this.imagen = imagen;
		this.productosComprados = new ArrayList<Producto>();
	}

	public Usuario(String nombre, String contrasenia, int presupuesto, double tiempoDisponible,
			Tipo tipoDeAtraccionPreferida, String imagen) {
		this.nombre = nombre;
		this.contrasenia = Crypt.hash(contrasenia);
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoDeAtraccionPreferida;
		this.imagen = imagen;
		this.productosComprados = new ArrayList<Producto>();
	}

	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public Tipo getTipoAtraccionPreferida() {
		return tipoAtraccionPreferida;
	}

	public String getImagen() {
		return imagen;
	}

	public boolean isNull() {
		return false;
	}

	public boolean checkPassword(String password) {
		return Crypt.match(password, this.contrasenia);
	}

	public List<Producto> getProductosComprados() {
		return this.productosComprados;
	}

	public String obtenerNombresdeProductosComprados() {
		String nombreProductosComprados = "";
		for (Producto p : productosComprados) {
			nombreProductosComprados += "\t\t\t\t\t" + p.getNombre() + "\r\n";
		}
		return nombreProductosComprados;
	}

	public int obtenerCostoTotalItinerario() {
		int costoTotal = 0;
		for (Producto p : productosComprados) {
			costoTotal += p.getCosto();
		}
		return costoTotal;
	}

	public double obtenerTiempoTotalItinerario() {
		double tiempoTotal = 0;
		for (Producto p : productosComprados) {
			tiempoTotal += p.getTiempo();
		}
		return tiempoTotal;
	}

	public List<Producto> obtenerListaNoOfertable() {
		List<Producto> productosNoOfertables = new ArrayList<Producto>();
		List<Atraccion> atraccionesDePromo = new ArrayList<>();
		if (this.productosComprados.size() != 0) {
			for (Producto producto : this.productosComprados) {
				if (producto.esPromocion()) {
					atraccionesDePromo = producto.obtenerAtracciones();
					productosNoOfertables.add(producto);
					for (Atraccion a : atraccionesDePromo) {
						productosNoOfertables.add(a);
					}
				} else {
					productosNoOfertables.add(producto);
				}
			}
		}
		return productosNoOfertables;
	}

	public boolean comproElProducto(Producto producto) {
		List<Producto> productosComprados = obtenerListaNoOfertable();
		List<Atraccion> atraccionesDePromo = new ArrayList<>();

		boolean comproElProducto = false;
		if (producto.esPromocion()) {
			atraccionesDePromo = producto.obtenerAtracciones();
			for (Atraccion atraccion : atraccionesDePromo) {
				if (productosComprados.contains(atraccion)) {
					comproElProducto = true;
					break;
				}
			}
		} else {
			comproElProducto = productosComprados.contains(producto);
		}
		return comproElProducto;
	}

	private void restarPresupuesto(int monto) {
		if (monto > 0) {
			this.presupuesto -= monto;
		}
	}

	private void restarTiempoDisponible(double tiempo) {
		if (tiempo > 0) {
			this.tiempoDisponible -= tiempo;
		}
	}

	public void reservarProducto(Producto producto) {
		double tiempo = producto.getTiempo();
		int costo = producto.getCosto();
		productosComprados.add(producto);
		producto.descontarCupo();
		restarTiempoDisponible(tiempo);
		restarPresupuesto(costo);
	}
	
	public boolean puedePagarlo(Producto producto) {
		return presupuesto >= producto.getCosto();
	}
	
	public boolean tieneTiempoDisponible(Producto producto) {
		return tiempoDisponible >= producto.getTiempo();
	}

	@Override
	public String toString() {
		String perfil = " 			.\n";
		perfil += "			|    Presupuesto de " + presupuesto + " monedas de oro \n";
		perfil += "			|  Tiempo de " + Reloj.conversor(tiempoDisponible) + " horas disponibles\n";
		perfil += "			| Preferencia por atracciones de " + tipoAtraccionPreferida.getNombre() + "\n";
		perfil += " 			×¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
		return perfil;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, presupuesto, tiempoDisponible, tipoAtraccionPreferida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre) && presupuesto == other.presupuesto
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible)
				&& tipoAtraccionPreferida == other.tipoAtraccionPreferida;
	}

	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if (!Pattern.matches("^[\\p{L} \\.'\\-]{3,150}$", nombre)) {
			errors.put("nombre", "El campo nombre debe contener al menos 3 caracteres. Ejemplo: Ari");
		}
		if (!Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", contrasenia)) {
			errors.put("contrasenia", "Debe contener al menos 8 caracteres, una minúscula, una"
					+ "mayúscula y un carácter especial sin puntos y comas. Ejemplo: Arilopez$12");
		}
		if (presupuesto < 3) {
			errors.put("presupuesto", "Por favor, ingresá un monto válido. El saldo mínimo son 3 monedas de oro.");
		}
		if (tiempoDisponible < 0.5) {
			errors.put("tiempoDisponible", "Por favor, ingresá horas decimales válidas. El tiempo mínimo es 0.5 hs");
		}
		if (tipoAtraccionPreferida == null) {
			errors.put("tipoAtraccionPreferida", "Por favor, eligí una de las opciones. Ejemplo: Aventura");
		}

		if (!Pattern.matches(
				"https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)",
				imagen)) {
			errors.put("imagen", "La imagen debe ser una url válida.");
		}

	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public void setTipoAtraccionPreferida(Tipo tipoAtraccionPreferida) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}