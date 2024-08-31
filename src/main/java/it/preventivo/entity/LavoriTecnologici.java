package it.preventivo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the lavori_tecnologici database table.
 *
 */
@Entity
@Table(name="lavori_tecnologici")
@NamedQuery(name="LavoriTecnologici.findAll", query="SELECT l FROM LavoriTecnologici l")
public class LavoriTecnologici implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Lob
	@Column(name="`﻿codice`")
	private String _codice;

	@Column(name = "descrizione", columnDefinition = "LONGTEXT")
    private String descrizione;
	

	//@Lob
	private double prezzo;

	@Lob
	@Column(name="quantita_misura_pezzi")
	private String quantitaMisuraPezzi;

	public LavoriTecnologici() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String get_codice() {
		return this._codice;
	}

	public void set_codice(String _codice) {
		this._codice = _codice;
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

	public String getQuantitaMisuraPezzi() {
		return this.quantitaMisuraPezzi;
	}

	public void setQuantitaMisuraPezzi(String quantitaMisuraPezzi) {
		this.quantitaMisuraPezzi = quantitaMisuraPezzi;
	}

}