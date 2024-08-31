package it.preventivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.preventivo.entity.Preventivo;
import it.preventivo.entity.Utente;

@Repository
public interface PreventivoRepository extends JpaRepository<Preventivo, Long> {
	// Puoi aggiungere metodi di query personalizzati se necessario
	
    /**
     * Trova il preventivo basato su un ID.
     * 
     * @param idPreventivo ID del preventivo da cercare.
	*/
	//Preventivo findByIdPreventivo(Long idPreventivo);
	/**
     * Trova i preventivi basati su un utente.
     * 
     * @param utente Utente per cui cercare i preventivi.
     * @return Lista dei preventivi corrispondenti all'utente fornito.*/
	//List<Preventivo> findByUtente(Utente utente);
	
	
	
}