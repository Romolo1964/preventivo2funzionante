package it.preventivo.entity;

import java.io.Serializable;
import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the categoria_lavori database table.
 *
 */
@Entity
@Table(name="categoria_lavori")
@NamedQuery(name="CategoriaLavori.findAll", query="SELECT c FROM CategoriaLavori c")
public class CategoriaLavori implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private BigInteger edili;

	private BigInteger elettrici;

	private BigInteger manutenzione;

	private BigInteger restauri;

	private BigInteger tecnologici;

	public CategoriaLavori() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getEdili() {
		return this.edili;
	}

	public void setEdili(BigInteger edili) {
		this.edili = edili;
	}

	public BigInteger getElettrici() {
		return this.elettrici;
	}

	public void setElettrici(BigInteger elettrici) {
		this.elettrici = elettrici;
	}

	public BigInteger getManutenzione() {
		return this.manutenzione;
	}

	public void setManutenzione(BigInteger manutenzione) {
		this.manutenzione = manutenzione;
	}

	public BigInteger getRestauri() {
		return this.restauri;
	}

	public void setRestauri(BigInteger restauri) {
		this.restauri = restauri;
	}

	public BigInteger getTecnologici() {
		return this.tecnologici;
	}

	public void setTecnologici(BigInteger tecnologici) {
		this.tecnologici = tecnologici;
	}

}