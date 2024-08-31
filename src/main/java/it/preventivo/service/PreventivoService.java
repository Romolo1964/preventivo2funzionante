package it.preventivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.preventivo.entity.Lavorazione;
import it.preventivo.entity.LavorazioniPreventivo;
import it.preventivo.entity.Preventivo;
import it.preventivo.entity.Utente;
import it.preventivo.repository.LavorazioneRepository;
import it.preventivo.repository.LavorazioniPreventivoRepository;
import it.preventivo.repository.PreventivoRepository;
import it.preventivo.repository.UtenteRepository;

import java.util.List;

@Service
public class PreventivoService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PreventivoRepository preventivoRepository;

    @Autowired
    private LavorazioneRepository lavorazioneRepository;

    @Autowired
    private LavorazioniPreventivoRepository lavorazioniPreventivoRepository;

    @Transactional
    public Preventivo creaPreventivo(Long utenteId, List<Long> idLavorazioni) {
        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        Preventivo preventivo = new Preventivo();
        preventivo.setUtente(utente);
        preventivo.setStato(Preventivo.StatoPreventivo.CREATO);

        double totale = 0.0;
        
        // Gestisci le lavorazioni selezionate
        for (Long idLavorazione : idLavorazioni) {
            Lavorazione lavorazione = lavorazioneRepository.findById(idLavorazione)
                    .orElseThrow(() -> new RuntimeException("Lavorazione non trovata"));

            LavorazioniPreventivo lavorazioniPreventivo = new LavorazioniPreventivo();
            lavorazioniPreventivo.setPreventivo(preventivo);
            lavorazioniPreventivo.setLavorazione(lavorazione);
            lavorazioniPreventivo.setCosto(lavorazione.getCosto());
            lavorazioniPreventivoRepository.save(lavorazioniPreventivo);

            totale += lavorazione.getCosto();
        }

        preventivo.setTotale(totale);
        return preventivoRepository.save(preventivo);
    }

    public Preventivo accettaPreventivo(Long preventivoId) {
        Preventivo preventivo = preventivoRepository.findById(preventivoId)
                .orElseThrow(() -> new RuntimeException("Preventivo non trovato"));

        preventivo.setStato(Preventivo.StatoPreventivo.ACCETTATO);
        return preventivoRepository.save(preventivo);
    }
}



