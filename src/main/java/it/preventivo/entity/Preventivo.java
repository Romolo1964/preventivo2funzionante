package it.preventivo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Preventivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utente utente;

    private double totale;

    private String stato;
    
    @Column(name = "tipoLavoro")
    private String tipoLavoro;
    
    
    @ManyToMany
    @JoinTable(
        name = "preventivo_lavorazione", 
        joinColumns = @JoinColumn(name = "preventivo_id"), 
        inverseJoinColumns = @JoinColumn(name = "lavorazione_id")
    )
    private List<Lavorazione> lavorazioni;
    
 // Aggiungi questo campo se desideri registrare la data di creazione
    @Column(name = "data_creazione")
    private LocalDate dataCreazione;

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public Preventivo() {
		super();
	}

	

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public List<Lavorazione> getLavorazioni() {
		return lavorazioni;
	}

	public void setLavorazioni(List<Lavorazione> lavorazioni) {
		this.lavorazioni = lavorazioni;
	}

	public void setDataCreazione(LocalDate now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Preventivo [id=" + id + ", utente=" + utente + ", totale=" + totale + ", stato=" + stato
				+ ", lavorazioni=" + lavorazioni + ", dataCreazione=" + dataCreazione + "]";
	}

	public void setTipoLavoro(String tipoLavoro2) {
		// TODO Auto-generated method stub
		
	}
    
    
    
}
