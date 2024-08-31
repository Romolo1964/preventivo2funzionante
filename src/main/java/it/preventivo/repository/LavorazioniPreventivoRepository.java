package it.preventivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.preventivo.entity.LavorazioniPreventivo;

public interface LavorazioniPreventivoRepository extends JpaRepository<LavorazioniPreventivo, Long> {
}
