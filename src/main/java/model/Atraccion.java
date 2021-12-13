package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import tierramedia.Producto;
import utils.Reloj;

public class Atraccion implements Producto {

	private int id;
	private String nombre, descripcion;
	private int costoDeVisita;
	private double tiempoDeVisita;
	private int cupoDePersonas;
	private Tipo tipo;
	private String imagen;
	
	private Map<String, String> errors;

	public Atraccion(int id, String nombre, String descripcion, int costoDeVisita, double tiempoDeVisita, int cupoDePersonas, Tipo tipo, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoDeVisita = costoDeVisita;
		this.tiempoDeVisita = tiempoDeVisita;
		this.cupoDePersonas = cupoDePersonas;
		this.tipo = tipo;
		this.imagen = imagen;
	}

	public Atraccion(String nombre, String descripcion, int costoDeVisita, double tiempoDeVisita, int cupoDePersonas, Tipo tipo, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoDeVisita = costoDeVisita;
		this.tiempoDeVisita = tiempoDeVisita;
		this.cupoDePersonas = cupoDePersonas;
		this.tipo = tipo;
		this.imagen = imagen;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public int getCosto() {
		return costoDeVisita;
	}

	@Override
	public double getTiempo() {
		return tiempoDeVisita;
	}
	
	public int getCostoDeVisita() {
		return costoDeVisita;
	}

	public int getCupoDePersonas() {
		return cupoDePersonas;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	public String getImagen() {
		return imagen;
	}

	public boolean hayCupo() {
		return this.cupoDePersonas > 0;
	}

	public double getTiempoDeVisita() {
		return tiempoDeVisita;
	}

	@Override
	public void descontarCupo() {
		this.cupoDePersonas--;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public List<Atraccion> obtenerAtracciones() {
		return List.of(this);
	}

	@Override
	public boolean puedeSerOfertadoA(Usuario u) {
		return this.hayCupo() && u.getPresupuesto() >= costoDeVisita && u.getTiempoDisponible() >= tiempoDeVisita;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCostoDeVisita(int costoDeVisita) {
		this.costoDeVisita = costoDeVisita;
	}

	public void setTiempoDeVisita(double tiempoDeVisita) {
		this.tiempoDeVisita = tiempoDeVisita;
	}

	public void setCupoDePersonas(int cupoDePersonas) {
		this.cupoDePersonas = cupoDePersonas;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (!Pattern.matches("^[\\p{L} \\.'\\-]{3,250}$", nombre)) {
			errors.put("nombre", "El campo nombre debe contener al menos 3 caracteres. Ejemplo: Ari");
		}
		if (descripcion.length() < 50 || descripcion.length() > 450) {
			errors.put("descripción", "El campo descripción debe contener entre 50 caracteres como mínimo y 450 como máximo.");
		}
		if (costoDeVisita < 3) {
			errors.put("costoDeVisita", " Por favor, ingresá un monto válido. El costo mínimo son 3 monedas de oro.");
		}
		if (tiempoDeVisita < 0.5) {
			errors.put("tiempoDeVisita", "Por favor ingresá un numero válido. El tiempo mínimo es 0.5 hs");
		}
		if (cupoDePersonas <= 0) {
			errors.put("cupoDePersonas", "Debe ser positivo");
		}
		if (tipo == null) {
			errors.put("tipo", "Por favor, eligí una de las opciones. Ejemplo: Aventura");
		}
		if (!Pattern.matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()!@:%_\\+.~#?&\\/\\/=]*)", imagen)) {
			errors.put("imagen", "La imagen debe ser una url válida.");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costoDeVisita, nombre, tiempoDeVisita, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costoDeVisita == other.costoDeVisita && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoDeVisita) == Double.doubleToLongBits(other.tiempoDeVisita)
				&& tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "Atraccion [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", costoDeVisita="
				+ costoDeVisita + ", tiempoDeVisita=" + Reloj.conversor(tiempoDeVisita) + ", cupoDePersonas=" + cupoDePersonas
				+ ", tipo=" + tipo + ", imagen=" + imagen + "]";
	}
	
}
