package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromoPorcentualTests {

	Promocion promo;
	
	Tipo AVENTURA = new Tipo(3, "Aventura");
	
	Atraccion atraccion1 = new Atraccion(6, "Bosque Negro","muy intenso", 25, 3, 4, AVENTURA, "fotoBn");
	Atraccion atraccion2 = new Atraccion(7, "Mordor","oscuro", 3, 4, 12, AVENTURA, "foto");

	@Before
	public void setUp() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);

		promo = new PromoPorcentual(3, "La Gran Aventura", "descripcion", ClaseDePromo.PROMO_PORCENTUAL, AVENTURA, atracciones, 20);
	}

	@Test
	public void obtenerCostoTest() {
		int costoDePromoEsperado = 23;
		assertEquals(costoDePromoEsperado, promo.getCosto());
	}

	@Test
	public void obtenerTiempoTotalTest() {
		double tiempoTotalEsperado = 7.00;
		assertEquals(tiempoTotalEsperado, promo.getTiempo(), 0);
	}
	
	@Test
	public void ahorroTest() {
		int ahorroEsperado = 5;
		assertEquals(ahorroEsperado, promo.ahorro());
	}

}