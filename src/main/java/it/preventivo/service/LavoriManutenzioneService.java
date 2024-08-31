package it.preventivo.service;

import java.util.List;
import java.util.Optional;

import it.preventivo.entity.LavoriManutenzione;

public interface LavoriManutenzioneService {
    List<LavoriManutenzione> findAll();
    Optional<LavoriManutenzione> findById(Long id);
    LavoriManutenzione save(LavoriManutenzione lavoriManutenzione);
    void deleteById(Long id);
	Object findLavoriByIds(List<Long> idLavorazioni);
}

