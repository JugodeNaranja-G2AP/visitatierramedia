package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromoAxBTests {

	Promocion promo;
	
	Tipo PAISAJE = new Tipo(1, "Paisaje");

	Atraccion atraccion1 = new Atraccion(1, "Minas Tirith", "Muy lindo", 5, 2, 25, PAISAJE, "fotito");
	Atraccion atraccion2 = new Atraccion(2, "Abismo de Helm","precioso", 5, 2, 15, PAISAJE, "fotito2");
	Atraccion atraccionGratis = new Atraccion(3,"Erebor", "ta bueno", 12, 3, 32, PAISAJE, "fotito3");

	@Before
	public void setUp() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		atracciones.add(atraccionGratis);
		promo = new PromoAxB(1, "Nuevas Maravillas", "todas cosas lindas", ClaseDePromo.PROMOAXB, PAISAJE, atracciones, atraccionGratis);

	}

	@Test
	public void getCostoDePackTest() {
		int costoDePromoEsperado = 10;
		assertEquals(costoDePromoEsperado, promo.getCosto());
	}

	@Test
	public void getTiempoDePackTest() {
		double tiempoEsperado = 7.00;
		assertEquals(tiempoEsperado, promo.getTiempo(), 0);
	}

	@Test
	public void ahorroTest() {
		int ahorroEsperado = 12;
		assertEquals(ahorroEsperado, promo.ahorro());
	}
}
