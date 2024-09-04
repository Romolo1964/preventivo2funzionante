package it.preventivo.service;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.preventivo.entity.Lavorazione;
import it.preventivo.repository.LavorazioneRepository;

@Service
public class LavorazioneServiceImpl implements LavorazioneService {

    private final LavorazioneRepository lavorazioneRepository;

    @Autowired
    public LavorazioneServiceImpl(LavorazioneRepository lavorazioneRepository) {
        this.lavorazioneRepository = lavorazioneRepository;
    }

    @Override
    public Lavorazione saveLavorazione(Lavorazione lavorazione) {
        return lavorazioneRepository.save(lavorazione);
    }

    @Override
    public Lavorazione getLavorazioneById(Long id) {
        return lavorazioneRepository.findById(id).orElse(null);
    }

    @Override
    public List<Lavorazione> getAllLavorazioni() {
        return lavorazioneRepository.findAll();
    }

    @Override
    public void deleteLavorazione(Long id) {
        lavorazioneRepository.deleteById(id);
    }

    @Override
    public List<Lavorazione> findByDescrizione(String descrizione) {
        return lavorazioneRepository.findByDescrizione(descrizione);
    }

    @Override
    public List<Lavorazione> findByCostoGreaterThan(double costo) {
        return lavorazioneRepository.findByCostoGreaterThan(costo);
    }

    @Override
    public List<Lavorazione> findByCostoLessThan(double costo) {
        return lavorazioneRepository.findByCostoLessThan(costo);
    }

    /**
     * Recupera una lista di lavorazioni in base a una lista di ID.
     * @param lavorazioneIds Lista di ID delle lavorazioni da recuperare.
     * @return Lista di lavorazioni corrispondenti agli ID forniti.
     */
    @Override
    public List<Lavorazione> getLavorazioniByIds(List<Long> lavorazioneIds) {
        if (lavorazioneIds == null || lavorazioneIds.isEmpty()) {
            // Se la lista di ID è null o vuota, restituisce una lista vuota.
            return Collections.emptyList();
        }
        // Utilizza il repository per recuperare le lavorazioni per gli ID forniti.
        return lavorazioneRepository.findAllById(lavorazioneIds);
    }
}
