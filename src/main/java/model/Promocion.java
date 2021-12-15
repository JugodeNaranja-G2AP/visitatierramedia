 package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import tierramedia.Producto;
import utils.Reloj;

public abstract class Promocion implements Producto {

	protected int id;
	private String nombre, descripcion;
	protected List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private double tiempoTotal;
	private int costoDePack;
	private Tipo tipo;
	private ClaseDePromo clase;

	public Promocion(int id, String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.atracciones = atracciones;
		this.clase = clase;
		
	}

	public Promocion(String nombre, String descripcion, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.atracciones = atracciones;
		this.clase = clase;
	
	}

	public int getId() {
		return id;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	public ClaseDePromo getClase() {
		return clase;
	}

	@Override
	public double getTiempo() {
		tiempoTotal = 0;
		for (Atraccion cadaAtraccion : atracciones) {
			tiempoTotal += cadaAtraccion.getTiempo();
		}
		return tiempoTotal;
	}

	@Override
	public int getCosto() {
		costoDePack = 0;
		for (Atraccion cadaAtraccion : atracciones) {
			costoDePack += cadaAtraccion.getCosto();
		}
		return costoDePack;
	}

	@Override
	public boolean hayCupo() {
		boolean hayCupo = true;
		for (Atraccion cadaAtraccion : atracciones) {
			if (!(cadaAtraccion.getCupoDePersonas() > 0)) {
				hayCupo = false;
				break;
			}
		}
		return hayCupo;
	}

	public int getCupoMin() {
        List <Integer> cupoDeAtracciones = new ArrayList<Integer>();
        for(Atraccion cadaAtraccion : atracciones) {
            cupoDeAtracciones.add(cadaAtraccion.getCupoDePersonas());
        }
        return Collections.min(cupoDeAtracciones);
    }
	
	@Override
	public void descontarCupo() {
		for (Atraccion cadaAtraccion : atracciones) {
			if (cadaAtraccion.getCupoDePersonas() > 0) {
				cadaAtraccion.descontarCupo();
			}
		}
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	public List<Atraccion> obtenerAtracciones() {
		return atracciones;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	@Override
	public boolean puedeSerOfertadoA(Usuario u) {
		return hayCupo() && u.getPresupuesto() >= this.getCosto() && u.getTiempoDisponible() >= this.getTiempo();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(atracciones, clase, costoDePack, descripcion, id, nombre, tiempoTotal, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atracciones, other.atracciones) && clase == other.clase
				&& costoDePack == other.costoDePack && Objects.equals(descripcion, other.descripcion) && id == other.id
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoTotal) == Double.doubleToLongBits(other.tiempoTotal)
				&& Objects.equals(tipo, other.tipo);
	}

	private String formatearAtracciones(List<Atraccion> atracciones) {
        String atraccionesFormateadas = "";
        for(Atraccion a: atracciones){
          atraccionesFormateadas +=  a ;
        }
        return atraccionesFormateadas;
    }

	@Override
	public String toString() {
		
		String perfil  = "\t\t\t\t PROMOCION "+ nombre + "\n";
		       perfil += "\t\t\t\t\t (" + tipo.getNombre() +")\n\n";
		       perfil +=" Atracciones que incluye " + formatearAtracciones(atracciones)+ "\n";
		       perfil += "\t|  Tiempo TOTAL: \t" + Reloj.conversor(getTiempo());
		       perfil += "  |  Costo:  \t" + (getCosto() + ahorro()) + " monedas de oro" + "\n";
		       perfil += descripcion();
		       perfil += "\t|\t\t\t\t   Costo TOTAL (c/desc):\t "  + getCosto() + " monedas de oro" + "\n";
	return perfil;
	}

	protected abstract String descripcion();

	public abstract int ahorro();
	
	public abstract String beneficio();

}