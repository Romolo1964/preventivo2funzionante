package it.preventivo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.preventivo.entity.Preventivo;
import it.preventivo.entity.StatoPreventivo;
import it.preventivo.entity.Utente;
//import it.preventivo.entity.StatoPreventivo;
import it.preventivo.repository.PreventivoRepository;
import it.preventivo.service.PreventivoService;

@Service
public class PreventivoServiceImpl implements PreventivoService {

    private final PreventivoRepository preventivoRepository;

    @Autowired
    public PreventivoServiceImpl(PreventivoRepository preventivoRepository) {
        this.preventivoRepository = preventivoRepository;
    }

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

	

    @Override
    public List<Preventivo> getPreventiviByDateRange(LocalDate startDate, LocalDate endDate) {
        return preventivoRepository.findByDataCreazioneBetween(startDate, endDate);
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
}
