package it.preventivo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lavorazione")
public class Lavorazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;
    private double costoUnitario;
    private int quantitaLavorazioni;
    private double costoTotale;
    private double costo;//Vecchio costo
    
    @ManyToMany(mappedBy = "lavorazioni")
    private List<Preventivo> preventivi;
    
	public double getCostoUnitario() {
		return costoUnitario;
	}
	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}
	public int getQuantitaLavorazioni() {
		return quantitaLavorazioni;
	}
	public void setQuantitaLavorazioni(int quantitaLavorazioni) {
		this.quantitaLavorazioni = quantitaLavorazioni;
	}
	public double getCostoTotale() {
		return costoTotale;
	}
	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	@Override
	public String toString() {
		return "Lavorazione [id=" + id + ", descrizione=" + descrizione + ", costoUnitario=" + costoUnitario
				+ ", quantitaLavorazioni=" + quantitaLavorazioni + ", costoTotale=" + costoTotale + ", costo=" + costo
				+ "]";
	}

    // Getters e Setters
    
}
