package it.preventivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.preventivo.entity.PreventivoLavorazione;

@Repository
public interface PreventivoLavorazioneRepository extends JpaRepository<PreventivoLavorazione, Long> {
}