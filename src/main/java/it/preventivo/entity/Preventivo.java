package it.preventivo.entity;

import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private StatoPreventivo stato;

    @OneToMany(mappedBy = "preventivo")
    private List<LavorazioniPreventivo> lavorazioni;

    // Getters e Setters

    public enum StatoPreventivo {
        CREATO, ACCETTATO, RIFIUTATO
    }

	public void setUtente(Utente utente2) {
		// TODO Auto-generated method stub
		
	}

	public void setStato(StatoPreventivo creato) {
		// TODO Auto-generated method stub
		
	}

	public void setTotale(double totale2) {
		// TODO Auto-generated method stub
		
	}
}
