package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;


import utils.Crypt;

public class Admin {
	
	private int id;
	private String nombre, contrasenia, correo;
	
	private Map<String, String> errors;
	
	public Admin(int id, String nombre, String contrasenia, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.correo = correo;
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

	public String getCorreo() {
		return correo;
	}
	
	public boolean isNull() {
		return false;
	}
	
	public boolean checkPassword(String password) {
		return Crypt.match(password, this.contrasenia);
	}
	
	public void setPassword(String password) {
		this.contrasenia = Crypt.hash(password);
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
		if (!Pattern.matches("[^@ \t\r\n]+@[^@ \t\r\n]+\\.[^@ \t\r\n]+", correo)) {
			errors.put("correo", "Debe ser válido. Ejemplo: agustin@gmail.com");
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

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", nombre=" + nombre + ", contrasenia=" + contrasenia + ", correo=" + correo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contrasenia, correo, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(contrasenia, other.contrasenia) && Objects.equals(correo, other.correo) && id == other.id
				&& Objects.equals(nombre, other.nombre);
	}
	
	

}
