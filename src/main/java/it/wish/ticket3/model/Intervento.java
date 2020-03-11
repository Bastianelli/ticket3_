package it.wish.ticket3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interventi")
public class Intervento {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String data;
	@Column(name="ora_inizio")
	private String inizio;
	@Column(name="ora_fine")
	private String fine;
	@Column(name="tempo_viaggio")
	private String viaggio;
	@Column(name="tempo_totale")
	private String tempoTotale;
	@Column(name="descrizione_intervento")
	private String descrizioneIntervento;

	public Intervento() {}

	
	//setta tempo totale con la somma del tempo di lavoro con il tempo di viaggio
	public void setTempoTotale() {
		this.tempoTotale = sommaTempi(tempoTrascorso(this.inizio, this.fine), this.viaggio); 
	}

	//somma due tempi
	public String sommaTempi(String tempo1, String tempo2) {
		if(tempo1 != null && tempo2 == null) {
			return sommaTempi(tempo1, "00:00");
		} else if(tempo1 == null && tempo2 != null){
			return sommaTempi("00:00", tempo2);
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
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getTempoTot() {
		return tempoTotale;
	}

	public void setTempoTot(String tempoTot) {
		this.tempoTotale = tempoTot;
	}

	public String getDescrizioneIntervento() {
		return descrizioneIntervento;
	}

	public void setDescrizioneIntervento(String descrizioneIntervento) {
		this.descrizioneIntervento = descrizioneIntervento;
	}

	@Override
	public String toString() {
		return "Intervento [id=" + id + ", data=" + data + ", inizio=" + inizio + ", fine=" + fine + ", viaggio="
				+ viaggio + ", tempoTot=" + tempoTotale + ", descrizioneIntervento=" + descrizioneIntervento + "]";
	}



}
