package it.preventivo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the lavori_manutenzione database table.
 */
@Entity
@Table(name="lavori_manutenzione")
@NamedQuery(name="LavoriManutenzione.findAll", query="SELECT l FROM LavoriManutenzione l")
public class LavoriManutenzione implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private long id;

    @Lob
    private String codice;

    @Column(name = "descrizione", columnDefinition = "LONGTEXT")
    private String descrizione;

    //@Lob
    private double prezzo;

//    @Lob
//    @Column(name="`sigla`") // Assicurati che questo corrisponda al nome esatto nel database
//    private String _sigla;

    public LavoriManutenzione() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodice() {
        return this.codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return this.prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

//    public String get_sigla() {
//        return this._sigla;
//    }
//
//    public void set_sigla(String _sigla) {
//        this._sigla = _sigla;
//    }
}
