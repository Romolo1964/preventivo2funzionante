package it.preventivo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="utente")
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idutente;

	@Column(name="categoria_lavori_id")
	private Long categoriaLavoriId;

	private String cognome;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creato_il")
	private Date creatoIl;

	@Temporal(TemporalType.DATE)
	@Column(name="data_di_nascita")
	private Date dataDiNascita;

	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modificato_il")
	private Date modificatoIl;

	private String nome;

	private String password;

	 @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
	    private List<Preventivo> preventivi;



	public Utente() {
	}

	public Long getIdutente() {
		return this.idutente;
	}

	public void setIdutente(Long idutente) {
		this.idutente = idutente;
	}

	public Long getCategoriaLavoriId() {
		return this.categoriaLavoriId;
	}

	public void setCategoriaLavoriId(Long categoriaLavoriId) {
		this.categoriaLavoriId = categoriaLavoriId;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getCreatoIl() {
		return this.creatoIl;
	}

	public void setCreatoIl(Date creatoIl) {
		
		this.creatoIl = creatoIl;
	}

	public Date getDataDiNascita() {
		return this.dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getModificatoIl() {
		return this.modificatoIl;
	}

	public void setModificatoIl(Date modificatoIl) {
		this.modificatoIl = modificatoIl;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}