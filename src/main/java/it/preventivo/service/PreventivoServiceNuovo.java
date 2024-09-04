package it.preventivo.service;

import it.preventivo.entity.Preventivo;
import it.preventivo.entity.Lavorazione;
import it.preventivo.repository.PreventivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PreventivoServiceNuovo {

    @Autowired
    private PreventivoRepository preventivoRepository;

    public void savePreventivo(Preventivo preventivo) {
        // Associa le lavorazioni selezionate al preventivo
        List<Lavorazione> lavorazioni = preventivo.getLavorazioni();
        preventivo.setLavorazioni(lavorazioni);

        // Salva il preventivo nel database
        preventivoRepository.save(preventivo);
    }
    
    // Altri metodi di servizio...
}

