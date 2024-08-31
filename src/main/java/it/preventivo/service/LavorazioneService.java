package it.preventivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.preventivo.entity.Lavorazione;
import it.preventivo.repository.LavorazioneRepository;

@Service
public class LavorazioneService {

    @Autowired
    private LavorazioneRepository lavorazioneRepository;

    public List<Lavorazione> findAll() {
        return lavorazioneRepository.findAll();
    }

    public Optional<Lavorazione> findById(Long id) {
        return lavorazioneRepository.findById(id);
    }

    public Lavorazione save(Lavorazione lavorazione) {
        return lavorazioneRepository.save(lavorazione);
    }

    public void deleteById(Long id) {
        lavorazioneRepository.deleteById(id);
    }
}
