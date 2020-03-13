package it.wish.ticket3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table( name = "rapporti")
public class Rapporto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Tecnico tecnico;
	private String note;
	@OneToMany
	private List<Componente> componenti = new ArrayList<>();
	@OneToOne
	private Intervento intervento;

	
	public Rapporto() {}
	
	public void  addComponente(Componente componente) {
		this.componenti.add(componente);
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Componente> getComponenti() {
		return componenti;
	}

	public void setComponenti(ArrayList<Componente> componenti) {
		this.componenti = componenti;
	}

	public Intervento getIntervento() {
		return intervento;
	}

	public void setIntervento(Intervento intervento) {
		this.intervento = intervento;
	}

	public void setComponenti(List<Componente> componenti) {
		this.componenti = componenti;
	}

	@Override
	public String toString() {
		return "Rapporto [id=" + id + ", cliente=" + cliente + ", tecnico=" + tecnico + ", note=" + note
				+ ", componenti=" + componenti + ", intervento=" + intervento + "]";
	}


	
}
