package it.preventivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.preventivo.entity.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    // Puoi aggiungere metodi di query personalizzati qui, se necessario
}

