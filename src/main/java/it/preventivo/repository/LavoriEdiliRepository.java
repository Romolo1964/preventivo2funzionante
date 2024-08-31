package it.preventivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.preventivo.entity.LavoriEdili;
/**
 * Repository per la gestione dei lavori edili.
 */
public interface LavoriEdiliRepository extends JpaRepository<LavoriEdili, Long> {
    // Puoi aggiungere eventuali query personalizzate qui, se necessario
	
	/**
     * Trova i lavori edili basati su una lista di ID.
     * 
     * @param ids Lista degli ID dei lavori da cercare.
     * @return Lista di lavori edili corrispondenti agli ID forniti.
     */
    List<LavoriEdili> findByIdIn(List<Long> ids);
	
}

