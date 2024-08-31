package it.preventivo.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.preventivo.entity.Preventivo;
import it.preventivo.entity.Utente;

@Repository
public interface PreventivoRepository extends JpaRepository<Preventivo, Long> {

    // Trova tutti i preventivi per un dato utente
    List<Preventivo> findByUtente(Utente utente);

    // Trova tutti i preventivi con uno specifico stato
    <StatoPreventivo> List<Preventivo> findByStato(StatoPreventivo stato);

    // Trova tutti i preventivi con un totale maggiore di una certa somma
    List<Preventivo> findByTotaleGreaterThan(double totale);

    // Trova tutti i preventivi con un totale minore di una certa somma
    List<Preventivo> findByTotaleLessThan(double totale);

	List<Preventivo> findByDataCreazioneBetween(LocalDate startDate, LocalDate endDate);
	
	
}
