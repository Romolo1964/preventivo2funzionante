package it.preventivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.preventivo.entity.LavoriTecnologici;

public interface LavoriTecnologiciRepository extends JpaRepository<LavoriTecnologici, Long> {
	// Puoi aggiungere eventuali query personalizzate qui, se necessario
	
	/**
     * Trova i lavori tecnologici basati su una lista di ID.
     * 
     * @param ids Lista degli ID dei lavori da cercare.
     * @return Lista di lavori tecnologici corrispondenti agli ID forniti.
     */
	List<LavoriTecnologici> findByIdIn(List<Long> ids);
}

