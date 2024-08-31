package it.preventivo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "preventivo")
public class Preventivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    private double totale;

    // Usa l'enumerazione per definire lo stato
    @Enumerated(EnumType.STRING)
    private StatoPreventivo stato;

    @OneToMany(mappedBy = "preventivo")
    private List<LavorazioniPreventivo> lavorazioni;
    
    // Aggiungi la proprietà 'dataCreazione'
    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;

    public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Preventivo() {
        super();
    }

    public Preventivo(Long id, Utente utente, double totale, StatoPreventivo stato, List<LavorazioniPreventivo> lavorazioni) {
        this.id = id;
        this.utente = utente;
        this.totale = totale;
        this.stato = stato;
        this.lavorazioni = lavorazioni;
    }

    // Getter e Setter

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

    public StatoPreventivo getStato() {
        return stato;
    }

    public void setStato(StatoPreventivo stato) {
        this.stato = stato;
    }

    public List<LavorazioniPreventivo> getLavorazioni() {
        return lavorazioni;
    }

    public void setLavorazioni(List<LavorazioniPreventivo> lavorazioni) {
        this.lavorazioni = lavorazioni;
    }
}
