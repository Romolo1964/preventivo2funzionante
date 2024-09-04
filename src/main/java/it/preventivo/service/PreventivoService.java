package it.preventivo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import it.preventivo.entity.Preventivo;
import it.preventivo.entity.Utente;
//import it.preventivo.entity.StatoPreventivo;

public interface PreventivoService {

    // Metodi CRUD di base
    Preventivo savePreventivo(Preventivo preventivo);
    Preventivo getPreventivoById(Long id);
    List<Preventivo> getAllPreventivi();
    void deletePreventivo(Long id);

    // Metodi di ricerca personalizzati
    List<Preventivo> getPreventiviByUtente(Utente utente);
   // List<Preventivo> getPreventiviByStato(StatoPreventivo stato);
    List<Preventivo> getPreventiviByTotaleGreaterThan(double totale);
    List<Preventivo> getPreventiviByTotaleLessThan(double totale);
    
 // Nuovi metodi
    Preventivo updatePreventivo(Long id, Preventivo preventivo); // Aggiorna un preventivo
   // long countPreventiviByStato(StatoPreventivo stato); // Conta i preventivi per stato
    List<Preventivo> getPreventiviOrderByTotaleDesc(); // Ottieni preventivi ordinati per totale
    List<Preventivo> getPreventiviByDateRange(LocalDate startDate, LocalDate endDate); // Filtra per intervallo di date
	List<Preventivo> findPreventiviByDateRange(LocalDateTime startDate, LocalDateTime endDate);
	Preventivo creaPreventivo(Long idUtente, String tipoLavoro, List<Long> idsLavorazioni);
}
