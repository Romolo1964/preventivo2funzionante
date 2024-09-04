package it.preventivo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.preventivo.entity.Lavorazione;
import it.preventivo.entity.Preventivo;
import it.preventivo.entity.StatoPreventivo;
import it.preventivo.entity.Utente;
import it.preventivo.repository.LavorazioneRepository;
//import it.preventivo.entity.StatoPreventivo;
import it.preventivo.repository.PreventivoRepository;
import it.preventivo.repository.UtenteRepository;
import it.preventivo.service.PreventivoService;

@Service
public class PreventivoServiceImpl implements PreventivoService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private LavorazioneRepository lavorazioneRepository;
    @Autowired
    private PreventivoRepository preventivoRepository;

    
    
    @Override
    public Preventivo savePreventivo(Preventivo preventivo) {
        return preventivoRepository.save(preventivo);
    }

    @Override
    public Preventivo getPreventivoById(Long id) {
        return preventivoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Preventivo> getAllPreventivi() {
        return preventivoRepository.findAll();
    }

    @Override
    public void deletePreventivo(Long id) {
        preventivoRepository.deleteById(id);
    }

    @Override
    public List<Preventivo> getPreventiviByUtente(Utente utente) {
        return preventivoRepository.findByUtente(utente);
    }

    public List<Preventivo> getPreventiviByStato(StatoPreventivo stato) {
        return preventivoRepository.findByStato(stato);
    }

    @Override
    public List<Preventivo> getPreventiviByTotaleGreaterThan(double totale) {
        return preventivoRepository.findByTotaleGreaterThan(totale);
    }

    @Override
    public List<Preventivo> getPreventiviByTotaleLessThan(double totale) {
        return preventivoRepository.findByTotaleLessThan(totale);
    }

    @Override
    public Preventivo updatePreventivo(Long id, Preventivo preventivo) {
        Preventivo existingPreventivo = preventivoRepository.findById(id).orElse(null);
        if (existingPreventivo != null) {
            existingPreventivo.setUtente(preventivo.getUtente());
            existingPreventivo.setTotale(preventivo.getTotale());
            existingPreventivo.setStato(preventivo.getStato());
            existingPreventivo.setLavorazioni(preventivo.getLavorazioni());
            return preventivoRepository.save(existingPreventivo);
        }
        return null;
    }
    
    /**
     * Crea un nuovo preventivo e lo salva nel database.
     *
     * @param utente L'utente associato al preventivo.
     * @param totale Il totale del preventivo.
     * @param stato  Lo stato iniziale del preventivo.
     * @return Il preventivo creato e salvato nel database.
     */
    public Preventivo creaPreventivo(Utente idUtente, String tipoLavoro, List<Lavorazione> idsLavorazioni) {
        Preventivo nuovoPreventivo = new Preventivo();
        nuovoPreventivo.setUtente(idUtente);
       // nuovoPreventivo.setTotale(totale);
        //nuovoPreventivo.setStato(stato);
        nuovoPreventivo.setLavorazioni(idsLavorazioni);
        nuovoPreventivo.setDataCreazione(LocalDate.now()); // Imposta la data di creazione come la data corrente

        // Salva il nuovo preventivo nel repository
        return preventivoRepository.save(nuovoPreventivo);
    }
    @Transactional
    public Preventivo creaPreventivo2(Long idUtente, String tipoLavoro, List<Long> idsLavorazioni) {
        // Recupera l'utente dal database
        Optional<Utente> utenteOpt = utenteRepository.findById(idUtente);
        if (!utenteOpt.isPresent()) {
            throw new RuntimeException("Utente non trovato con id: " + idUtente);
        }

        // Recupera le lavorazioni dal database
        List<Lavorazione> lavorazioni = lavorazioneRepository.findAllById(idsLavorazioni);
        if (lavorazioni.isEmpty()) {
            throw new RuntimeException("Lavorazioni non trovate con gli id forniti.");
        }

        // Crea il preventivo
        Preventivo preventivo = new Preventivo();
        preventivo.setUtente(utenteOpt.get());
        preventivo.setTipoLavoro(tipoLavoro);
        preventivo.setLavorazioni(lavorazioni);

        // Salva il preventivo nel database
        return preventivoRepository.save(preventivo);
    }

    @Override
    public List<Preventivo> getPreventiviByDateRange(LocalDate startDate, LocalDate endDate) {
        return preventivoRepository.findByDataCreazioneBetween(startDate, endDate);
    }
    public void savePreventivo1(Preventivo preventivo) {
        // Associa le lavorazioni selezionate al preventivo
        List<Lavorazione> lavorazioni = preventivo.getLavorazioni();
        preventivo.setLavorazioni(lavorazioni);

        // Salva il preventivo nel database
        preventivoRepository.save(preventivo);
    }
	@Override
	public List<Preventivo> getPreventiviOrderByTotaleDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Preventivo> findPreventiviByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Preventivo creaPreventivo(Long idUtente, String tipoLavoro, List<Long> idsLavorazioni) {
		// TODO Auto-generated method stub
		return null;
	}
}
