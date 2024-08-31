package it.preventivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.preventivo.entity.Lavorazione;

@Repository
public interface LavorazioneRepository extends JpaRepository<Lavorazione, Long> {
}