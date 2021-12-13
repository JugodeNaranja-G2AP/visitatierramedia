package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuariosTest {
	
	Tipo PAISAJE = new Tipo(1, "Paisaje");
	Tipo DEGUSTACION = new Tipo(2, "Degustacion");
	Tipo AVENTURA = new Tipo(3, "Aventura");

	Usuario usuarioPrueba = new Usuario(1, "UsuarioPrueba","password", 12, 7, DEGUSTACION, "fotoPerfil");
	// String nombre, double costoDeVisita, int tiempoDeVisita, int cupoDePersonas,
	// Tipo tipo
	Atraccion atraccion1 = new Atraccion(1, "Minas Tirith","descrip1", 5, 2, 25, PAISAJE, "fotoPai");
	Atraccion atraccion2 = new Atraccion(2, "Abismo de Helm","descrip2", 5, 2, 15, DEGUSTACION, "fotoDegus");
	Atraccion atraccion3 = new Atraccion(3, "Erebor","descrip3", 12, 3, 32, AVENTURA, "fotoAven");

	@Before
	public void setUp() {

		usuarioPrueba.reservarProducto(atraccion1);
		usuarioPrueba.reservarProducto(atraccion2);
	}

	@Test
	public void obtenerTiempoTotalItinerarioTest() {

		double tiempoEsperado = 4;
		assertEquals(tiempoEsperado, usuarioPrueba.obtenerTiempoTotalItinerario(), 0);
	}

	@Test
	public void obtenerCostoTotalItinerarioTest() {
		int costoEsperado = 10;
		assertEquals(costoEsperado, usuarioPrueba.obtenerCostoTotalItinerario(), 0);
	}

	@Test
	public void presupuestoTest() {
		int presupuestoEsperado = 2;
		assertEquals(presupuestoEsperado, usuarioPrueba.getPresupuesto());

	}

	@Test
	public void tiempoDisponibleTest() {
		double tiempoEsperado = 3;
		assertEquals(tiempoEsperado, usuarioPrueba.getTiempoDisponible(), 0);
	}

	@Test
	public void comproElProductoTest() {
		assertTrue(usuarioPrueba.comproElProducto(atraccion1));
		assertTrue(usuarioPrueba.comproElProducto(atraccion2));
		assertFalse(usuarioPrueba.comproElProducto(atraccion3));
	}

	@Test
	public void obtenerNombresdeProductosCompradosTest() {
		String nombresEsperados = "\t\t\t\t\tMinas Tirith\r\n\t\t\t\t\tAbismo de Helm\r\n";
		assertEquals(nombresEsperados, usuarioPrueba.obtenerNombresdeProductosComprados());
	}

}
