package it.preventivo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.preventivo.entity.LavoriElettrici;
import it.preventivo.entity.LavoriRestauro;

public interface LavoriElettriciRepository extends JpaRepository<LavoriElettrici, Long> {
    // Puoi aggiungere eventuali query personalizzate qui, se necessario
	
	/**
     * Trova i lavori elettrici basati su una lista di ID.
     * 
     * @param idLavorazioni Lista degli ID dei lavori da cercare.
     * @return Lista di lavori elettrici corrispondenti agli ID forniti.
     */
	 
   // List<LavoriElettrici> findByIdIn(List<String> idLavorazioni);
    List<LavoriRestauro> findByIdIn(List<String> idLavorazioni);
    
}
