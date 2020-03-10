package it.wish.ticket3.model;

public class Componente {
	private String descrizione;
	private Double costoUnitario;
	private Integer quantita;
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

	@Override
	public String toString() {
		return "Componente [descrizione=" + descrizione + ", costoUnitario=" + costoUnitario + ", quantita=" + quantita
				+ ", costoTot=" + costoTot + "]";
	}
	
	
}
