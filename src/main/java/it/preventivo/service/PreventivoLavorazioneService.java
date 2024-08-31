package it.preventivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.preventivo.entity.PreventivoLavorazione;
import it.preventivo.repository.PreventivoLavorazioneRepository;

@Service
public class PreventivoLavorazioneService {

    @Autowired
    private PreventivoLavorazioneRepository preventivoLavorazioneRepository;

    public List<PreventivoLavorazione> findAll() {
        return preventivoLavorazioneRepository.findAll();
    }

    public Optional<PreventivoLavorazione> findById(Long id) {
        return preventivoLavorazioneRepository.findById(id);
    }

    public PreventivoLavorazione save(PreventivoLavorazione preventivoLavorazione) {
        return preventivoLavorazioneRepository.save(preventivoLavorazione);
    }

    public void deleteById(Long id) {
        preventivoLavorazioneRepository.deleteById(id);
    }
}

