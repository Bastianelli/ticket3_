package it.wish.ticket3.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InterventoTest {
	private Intervento intervento1 = new Intervento();
	private Intervento intervento2 = new Intervento();
	private Intervento intervento3 = new Intervento();
	private String tempoTrascorsoAtteso1, tempoTrascorsoAtteso2, tempoTrascorsoAtteso3;
	private String tempo1, tempo2, tempo3, tempo4,tempo5;
	private String tempoSommaAtteso12, tempoSommaAtteso34, tempoSommaAtteso35;
	private String tempoTotaleAtteso1, tempoTotaleAtteso2, tempoTotaleAtteso3;
	
	
	@BeforeEach
	void setUp() throws Exception {
		//testTempoTrascorso
		intervento1.setInizio("10:00");
		intervento1.setFine("11:00");
		intervento1.setViaggio("02:00");
		tempoTrascorsoAtteso1 = "01:00";
		tempoTotaleAtteso1 = "03:00";
		
		intervento2.setInizio("10:40");
		intervento2.setFine("13:20");
		intervento2.setViaggio("02:40");
		tempoTrascorsoAtteso2 = "02:40";
		tempoTotaleAtteso2 = "05:20";
		
		intervento3.setInizio("10:00");
		intervento3.setFine("11:30");
		intervento3.setViaggio("00:40");
		tempoTrascorsoAtteso3 = "01:30";
		tempoTotaleAtteso3 = "02:10";
		
		//testSommaTempi
		tempo1 = "02:45";
		tempo2 = "01:05";
		tempo3 = "00:40";
		tempo4 = "05:30";
		tempo5 = null;
		
		tempoSommaAtteso12 ="03:50";
		tempoSommaAtteso34 ="06:10";
		tempoSommaAtteso35 ="00:40";
		
	}
	
	@Test
	void testTempoTrascorso() {
		assertEquals(tempoTrascorsoAtteso1, intervento1.tempoTrascorso());
		assertEquals(tempoTrascorsoAtteso2, intervento2.tempoTrascorso());
		assertEquals(tempoTrascorsoAtteso3, intervento3.tempoTrascorso());
	}

	
	@Test
	void testSommaTempi() {
		assertEquals(tempoSommaAtteso12, intervento1.sommaTempi(tempo1, tempo2));
		assertEquals(tempoSommaAtteso34, intervento1.sommaTempi(tempo3, tempo4));
		assertEquals(tempoSommaAtteso35, intervento1.sommaTempi(tempo3, tempo5));
		assertEquals(tempoSommaAtteso35, intervento1.sommaTempi(tempo5, tempo3));
		assertNull(intervento1.sommaTempi(tempo5, tempo5));
		
	}

	@Test
	//@Disabled
	void testSetTempoTotale() {
		intervento1.setTempoTotale();
		intervento2.setTempoTotale();
		intervento3.setTempoTotale();
		assertEquals(tempoTotaleAtteso1, intervento1.getTempoTot());
		assertEquals(tempoTotaleAtteso2, intervento2.getTempoTot());
		assertEquals(tempoTotaleAtteso3, intervento3.getTempoTot());
	}


}
