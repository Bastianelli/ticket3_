package it.wish.ticket3.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import it.wish.ticket3.model.Intervento;
import it.wish.ticket3.model.Rapporto;

class RapportoServiceTest {
	private RapportoService rapportoService = new RapportoService();
	private List<Rapporto> rapporti = new ArrayList<>();
	private List<Rapporto> rapportiTrovati;
	private Rapporto rapporto1 = new Rapporto();
	private Rapporto rapporto2 = new Rapporto();
	private Rapporto rapporto3 = new Rapporto();
	private Intervento intervento1 = new Intervento();
	private Intervento intervento2 = new Intervento();
	private Intervento intervento3 = new Intervento();

	@BeforeEach
	
	void setUp() throws Exception {
		rapportiTrovati = new ArrayList<>();
		intervento1.setData("2020-01-15");
		intervento2.setData("2020-02-15");
		intervento3.setData("2010-02-15");
		rapporto1.setIntervento(intervento1);
		rapporto1.setId(1);
		rapporto2.setIntervento(intervento2);
		rapporto1.setId(2);
		rapporto3.setIntervento(intervento3);
		rapporto1.setId(3);
		rapporti.add(rapporto1);
		rapporti.add(rapporto2);
		rapporti.add(rapporto3);
		
	}

	@Test
	void testFiltraRapporti() {
		assertTrue(rapportiTrovati.isEmpty());
		rapportiTrovati = rapportoService.filtraRapporti(rapporti, "2020", "02");
		assertFalse(rapportiTrovati.isEmpty());
		for(Rapporto rapporto : rapportiTrovati) {
			assertEquals("2020", rapporto.getIntervento().getAnno());
			assertEquals("02", rapporto.getIntervento().getMese());
		}	
		
	}

	@Test
	void testFindByAnnoListOfRapportoString() {
		assertNotNull(rapporti);
		assertFalse(rapporti.isEmpty());
		assertEquals(3, rapporti.size());
		assertEquals("2020", rapporti.get(0).getIntervento().getAnno());
		assertEquals("2020", rapporti.get(1).getIntervento().getAnno());
		assertEquals("2010", rapporti.get(2).getIntervento().getAnno());
		assertTrue(rapportiTrovati.isEmpty());
//		rapportiTrovati.addAll(rapportoService.findByAnno(rapporti, "2020"));

		rapportiTrovati = rapportoService.findByAnno(rapporti, "2020");
		assertFalse(rapportiTrovati.isEmpty());
		assertEquals(2, rapportiTrovati.size());
		for(Rapporto rapporto : rapportiTrovati) {
			assertEquals("2020", rapporto.getIntervento().getAnno());
		}	
		rapportiTrovati.clear();
		assertTrue(rapportiTrovati.isEmpty());
		rapportiTrovati.addAll(rapportoService.findByAnno(rapporti, "2010"));
		assertFalse(rapporti.isEmpty());
		assertEquals(1, rapportiTrovati.size());
		assertEquals("2010", rapportiTrovati.get(0).getIntervento().getAnno());
	}

	@Test
	@Disabled
	void testFindByAnnoString() {
		assertNotNull(rapporti);
		assertFalse(rapporti.isEmpty());
		assertEquals(3, rapporti.size());
		assertEquals("2020", rapporti.get(0).getIntervento().getAnno());
		assertEquals("2020", rapporti.get(1).getIntervento().getAnno());
		assertEquals("2010", rapporti.get(2).getIntervento().getAnno());
		assertTrue(rapportiTrovati.isEmpty());
//		rapportiTrovati.addAll(rapportoService.findByAnno(rapporti, "2020"));

		rapportiTrovati = rapportoService.findByAnno("2020");
		assertFalse(rapportiTrovati.isEmpty());
		assertEquals(2, rapportiTrovati.size());
		for(Rapporto rapporto : rapportiTrovati) {
			assertEquals("2020", rapporto.getIntervento().getAnno());
		}	
		rapportiTrovati.clear();
		assertTrue(rapportiTrovati.isEmpty());
		rapportiTrovati.addAll(rapportoService.findByAnno("2010"));
		assertFalse(rapporti.isEmpty());
		assertEquals(1, rapportiTrovati.size());
		assertEquals("2010", rapportiTrovati.get(0).getIntervento().getAnno());
	}

	@Test
	void testFindByMeseListOfRapportoString() {
		assertNotNull(rapporti);
		assertFalse(rapporti.isEmpty());
		assertEquals(3, rapporti.size());
		assertEquals("01", rapporti.get(0).getIntervento().getMese());
		assertEquals("02", rapporti.get(1).getIntervento().getMese());
		assertEquals("02", rapporti.get(2).getIntervento().getMese());
		assertTrue(rapportiTrovati.isEmpty());
		rapportiTrovati.addAll(rapportoService.findByMese(rapporti, "02"));
		assertFalse(rapportiTrovati.isEmpty());
		assertEquals(2, rapportiTrovati.size());
		for(Rapporto rapporto : rapportiTrovati) {
			assertEquals("02", rapporto.getIntervento().getMese());
		}	
		rapportiTrovati.clear();
		assertTrue(rapportiTrovati.isEmpty());
		rapportiTrovati.addAll(rapportoService.findByMese(rapporti, "01"));
		assertFalse(rapporti.isEmpty());
		assertEquals(1, rapportiTrovati.size());
		assertEquals("01", rapportiTrovati.get(0).getIntervento().getMese());
	}

}
