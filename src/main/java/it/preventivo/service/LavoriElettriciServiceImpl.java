package it.preventivo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import it.preventivo.entity.LavoriElettrici;
import it.preventivo.repository.LavoriElettriciRepository;

/**
 * Implementazione del servizio per i lavori elettrici.
 */
@Service
public class LavoriElettriciServiceImpl  {

    @Autowired
    private LavoriElettriciRepository repository;

   // @Override
    public List<LavoriElettrici> findAll() {
        return repository.findAll();
    }

    //@Override
    public LavoriElettrici findById(long id) {
        Optional<LavoriElettrici> result = repository.findById(id);
        return result.orElse(null);
    }

   // @Override
    public LavoriElettrici save(LavoriElettrici lavoroElettrico) {
        return repository.save(lavoroElettrico);
    }

   // @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    /**
     * Trova i lavori elettrici basati su una lista di ID.
     * 
     * @param idLavorazioni Lista degli ID dei lavori da cercare.
     * @return Lista di lavori elettrici corrispondenti agli ID forniti.
     */
    //@Override
    public List<LavoriElettrici> findLavoriByIds(List<Long> idLavorazioni) {
        return repository.findByIdIn(idLavorazioni);
    }
}	