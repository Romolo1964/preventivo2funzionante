package it.preventivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.preventivo.entity.LavoriRestauro;

public interface LavoriRestauroRepository extends JpaRepository<LavoriRestauro, Long> {
	
	// Puoi aggiungere eventuali query personalizzate qui, se necessario
	// Ad esempio:
	 /**
     * Trova i lavori di restauro basati su una lista di ID.
     * 
     * @param ids Lista degli ID dei lavori da cercare.
     * @return Lista di lavori di restauro corrispondenti agli ID forniti.
     */
    List<LavoriRestauro> findByIdIn(List<Long> ids);
}
