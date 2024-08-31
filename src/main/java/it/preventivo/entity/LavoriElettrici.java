package it.preventivo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the lavori_elettrici database table.
 *
 */
@Entity
@Table(name="lavori_elettrici")
@NamedQuery(name="LavoriElettrici.findAll", query="SELECT l FROM LavoriElettrici l")
public class LavoriElettrici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Lob
	private String codice;

	@Column(name = "descrizione", columnDefinition = "LONGTEXT")
	private String descrizione;

	@Lob
	private String prezzo;

	@Lob
	@Column(name="quantita_misura_pezzi")
	private String quantitaMisuraPezzi;

	@Lob
	private String sigla;

	public LavoriElettrici() {
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

	public String getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public String getQuantitaMisuraPezzi() {
		return this.quantitaMisuraPezzi;
	}

	public void setQuantitaMisuraPezzi(String quantitaMisuraPezzi) {
		this.quantitaMisuraPezzi = quantitaMisuraPezzi;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}