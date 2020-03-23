/**
 * 
 */
package it.wish.ticket3.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 * @author Bastianelli Michele
 *
 */
@Entity
@Table( name = "clienti")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	@NotNull
	private String denominazione;
	@NotNull
	private String indirizzo;
	@NotNull
	@Column(name="localita")
	private String localita;
	@NotNull
	private String cap;
	@NotNull
	@Column(name="citta")
	private String citta;
	@NotNull
	@Column(name="partita_iva")
	private String partitaIva;
	@OneToMany
	private List<Rapporto> rapporti = new ArrayList<>();
	
	public Cliente() {}
	
	
	public void addRapporto(Rapporto rapporto) {
		this.rapporti.add(rapporto);
	}
	
	//getter e setter
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}


	public List<Rapporto> getRapporti() {
		return rapporti;
	}


	public void setRapporti(List<Rapporto> rapporti) {
		this.rapporti = rapporti;
	}


	@Override
	public String toString() {
		return "Cliente [Id=" + Id + ", denominazione=" + denominazione + ", indirizzo=" + indirizzo + ", localita="
				+ localita + ", cap=" + cap + ", citta=" + citta + ", partitaIva=" + partitaIva + "]";
	}



	
	
}
