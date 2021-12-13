package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class Tipo {

	private int id;
	private String nombre;
	
	private Map<String, String> errors;
	
	public Tipo(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (!Pattern.matches("^[\\p{L} \\.'\\-]{5,150}$", nombre)) {
			errors.put("nombre", "El campo nombre debe contener al menos 5 caracteres. Ejemplo: Playa");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipo other = (Tipo) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}
}
