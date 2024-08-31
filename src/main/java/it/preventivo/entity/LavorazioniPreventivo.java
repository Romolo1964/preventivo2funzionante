package it.preventivo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lavorazioni_preventivo")
public class LavorazioniPreventivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "preventivo_id")
    private Preventivo preventivo;

    @ManyToOne
    @JoinColumn(name = "lavorazione_id")
    private Lavorazione lavorazione;

    private double costo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Preventivo getPreventivo() {
		return preventivo;
	}

	public void setPreventivo(Preventivo preventivo) {
		this.preventivo = preventivo;
	}

	public Lavorazione getLavorazione() {
		return lavorazione;
	}

	public void setLavorazione(Lavorazione lavorazione) {
		this.lavorazione = lavorazione;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

    // Getters e Setters
    
}
