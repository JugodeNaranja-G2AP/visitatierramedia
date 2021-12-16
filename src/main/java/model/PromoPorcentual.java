package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class PromoPorcentual extends Promocion {

	private int porcentajeDescuento;
	private Map<String, String> errors;
	
	public PromoPorcentual(int id, String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			int porcentajeDescuento) {
		super(id, nombre, descripcion, clase, tipo, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public PromoPorcentual(String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			int porcentajeDescuento) {
		super(nombre, descripcion, clase, tipo, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	private int realizarDescuento(int costo) {
		int descuento = (int) Math.floor((costo * this.porcentajeDescuento) / 100);
		return descuento;
	}

	@Override
	public int getCosto() {
		int costoDePack = super.getCosto();
		return costoDePack - realizarDescuento(costoDePack);
	}

	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	@Override
	public int ahorro() {
		return realizarDescuento(super.getCosto());
	}

	@Override
	public String descripcion() {
		String perfil = "\t|\t\t\t\t\t     |  Descuento:\t " + ahorro() + " monedas de oro" + "\n";
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
		if (porcentajeDescuento < 0 || porcentajeDescuento > 60) {
			errors.put("porcentajeDescuento", "Por favor, ingresá un monto de descuento asociado a la promo.");
		}
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

}