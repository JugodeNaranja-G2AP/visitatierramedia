package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class PromoAxB extends Promocion {

	private Atraccion atraccionGratis;
	private Map<String, String> errors;

	public PromoAxB(int id, String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones, 
			Atraccion atraccionGratis) {
		super(id, nombre, descripcion, clase, tipo, atracciones);
		this.atraccionGratis = atraccionGratis;
	}

	public PromoAxB(String nombre,String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			Atraccion atraccionGratis) {
		super(nombre, descripcion, clase, tipo, atracciones);
		this.atraccionGratis = atraccionGratis;
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	@Override
	public int getCosto() {
		return super.getCosto() - this.atraccionGratis.getCosto();
	}

	@Override
	public int ahorro() {
		return atraccionGratis.getCosto();
	}

	@Override
	public String descripcion() {
		String perfil = "\t|\t\t\t\t\t      |  Descuento:\t " + ahorro() + " monedas de oro" + "\n";
		perfil += "\t×¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
		return perfil;
	}
	
	@Override
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}
	
	public void validate() {
		errors = new HashMap<String, String>();

		if (!Pattern.matches("^[\\p{L} \\.'\\-]{3,250}$", super.getNombre())) {
			errors.put("nombre", "El campo nombre debe contener al menos 3 caracteres. Ejemplo: Río");
		}
		if (super.getDescripcion().length() < 50 || super.getDescripcion().length() > 450) {
			errors.put("descripcion", "El campo descripción debe contener entre 50 caracteres como mínimo y 450 como máximo.");
		}
		if (super.getClase() == null) {
			errors.put("clase", "Por favor, seleccioná un tipo de promo.");
		}
		if (super.getTipo() == null) {
			errors.put("tipo", "Por favor, eligí una de las opciones. Ejemplo: Aventura");
		}
		if (super.getAtracciones() == null) {
			errors.put("atracciones", "Por favor, seleccioná al menos una atracción.");
		}
		if (atraccionGratis == null) {
			errors.put("atraccionGratis", "Por favor, seleccioná la atracción sin cargo.");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setAtraccionGratis(Atraccion atraccionGratis) {
		this.atraccionGratis = atraccionGratis;
	}

	@Override
	public String beneficio() {
		return atraccionGratis.getNombre();
	}
}
