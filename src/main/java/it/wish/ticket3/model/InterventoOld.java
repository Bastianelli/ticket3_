package it.wish.ticket3.model;


import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Entity
public class InterventoOld {
	@NotNull
	@Pattern(regexp="\\d{2}/\\d{2}/\\d{4}")
	private String data;
	@NotNull
	@Pattern(regexp="\\d{2}:\\d{2}")
	private String inizio;
	@NotNull
	@Pattern(regexp="\\d{2}:\\d{2}")
	private String fine;
	
	@Pattern(regexp="\\d{2}:\\d{2}")
	private String viaggio;
	
	@Pattern(regexp="\\d{2}:\\d{2}")
	private String tempoTotale;
	@NotNull
	private String tipologiaIntervento;
	
	
	
	//setta tempo totale con la somma del tempo di lavoro con il tempo di viaggio
	public void setTempoTotale() {
		this.tempoTotale = sommaTempo(tempoTrascorso(this.inizio, this.fine), this.viaggio); 
	}

	//somma due tempi
	public String sommaTempo(String tempo1, String tempo2) {
		if(tempo1 != null && tempo2 == null) {
			return sommaTempo(tempo1, "00:00");
		} else if(tempo1 == null && tempo2 != null){
			return sommaTempo("00:00", tempo2);
		}
		Integer ora1, ora2, min1, min2, oraTot, minTot;
		ora1 = oreStringToInt(tempo1);
		ora2 = oreStringToInt(tempo2);
		min1 = minutiStringToInt(tempo1);
		min2 = minutiStringToInt(tempo2);
		
		if((min1 + min2)<60) {
			oraTot = ora1 + ora2;
			minTot = min1 + min2;
		} else {
			oraTot = (ora1 + ora2) + 1;
			minTot = (min1 + min2) - 60;
		}
		
		return tempoIntToString(oraTot, minTot);
	}
	
	
	//calcola il tempo trascorso tra un tempo iniziale ed uno finale
	public String tempoTrascorso(String inizio, String fine) {
		Integer oraInizio, oraFine, minInizio, minFine, oraTot, minTot;
		oraInizio = oreStringToInt(inizio);
		oraFine = oreStringToInt(fine);
		minInizio = minutiStringToInt(inizio);
		minFine = minutiStringToInt(fine);
		
		if (minInizio == minFine) {
			oraTot = (oraFine - oraInizio)+1;
			minTot = 0;
		} else if(minInizio > minFine){
			oraTot = oraFine - oraInizio;
			minTot = (60 - minInizio) + minFine;
		}else {
			oraTot = oraFine - oraInizio;
			minTot = minFine - minInizio;
		}
		
		return tempoIntToString(oraTot, minTot);
	}
	
	
	//ritorna il valore delle ora Integer dato un tempo nel formato String "00:00"
	public Integer oreStringToInt(String tempo) {
		 return Integer.parseInt(tempo.substring(0, 2));
	}
	
	//ritorna il valore dei minuti Integer dato un tempo nel formato String "00:00"
	public Integer minutiStringToInt(String tempo) {
		 return Integer.parseInt(tempo.substring(3, 5));
	}
	//ritorna una stringa nel formato "00:00" che rappresenta ore e minuti
	public String tempoIntToString(Integer ora, Integer min) {
		return String.format("%02d", ora) + ":" + String.format("%02d", min);
	}


	//getter e setter
	public String getData() {
		return data;
	}




	public void setData(String data) {
		this.data = data;
	}




	public String getInizio() {
		return inizio;
	}




	public void setInizio(String inizio) {
		this.inizio = inizio;
	}




	public String getFine() {
		return fine;
	}




	public void setFine(String fine) {
		this.fine = fine;
	}




	public String getViaggio() {
		return viaggio;
	}




	public void setViaggio(String viaggio) {
		this.viaggio = viaggio;
	}




	public String getTempoTotale() {
		return tempoTotale;
	}




	public void setTempoTotale(String tempoTotale) {
		this.tempoTotale = tempoTotale;
	}




	public String getTipologiaIntervento() {
		return tipologiaIntervento;
	}




	public void setTipologiaIntervento(String tipologiaIntervento) {
		this.tipologiaIntervento = tipologiaIntervento;
	}


	@Override
	public String toString() {
		return "Intervento [data=" + data + ", inizio=" + inizio + ", fine=" + fine + ", viaggio=" + viaggio
				+ ", tempoTotale=" + tempoTotale + ", tipologiaIntervento=" + tipologiaIntervento + "]";
	}
}
