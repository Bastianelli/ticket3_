package it.wish.ticket3.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComponenteTest {
	private Componente componente1 = new Componente();
	private Componente componente2 = new Componente();
	private Double uno = 5D, due = 15D;

	@Before
	public void setUp() throws Exception {
		componente1.setQuantita(1);
		componente1.setCostoUnitario(5D);
		componente2.setQuantita(3);
		componente2.setCostoUnitario(5D);
	}

	@Test
	public void testCalcolaCostoTotale() {
		componente1.calcolaCostoTotale();
		componente2.calcolaCostoTotale();
		assertNotNull(componente1.getCostoTot());
		assertNotNull(componente2.getCostoTot());
		assertEquals(uno, componente1.getCostoTot());
		assertEquals(due, componente2.getCostoTot());
		
	}

}
