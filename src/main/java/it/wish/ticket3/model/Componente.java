package it.wish.ticket3.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "componenti")
public class Componente {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String descrizione;
	@Column(name = "costo_unitario")
	private Double costoUnitario;
	private Integer quantita;
	@Column(name = "costo_totale")
	private Double costoTot;
	
	public Componente () {}
	
	public void calcolaCostoTotale() {
		this.costoTot = this.costoUnitario * this.quantita;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Double getCostoTot() {
		return costoTot;
	}

	public void setCostoTot(Double costoTot) {
		this.costoTot = costoTot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Componente [id=" + id + ", descrizione=" + descrizione + ", costoUnitario=" + costoUnitario
				+ ", quantita=" + quantita + ", costoTot=" + costoTot + "]";
	}

	
}
