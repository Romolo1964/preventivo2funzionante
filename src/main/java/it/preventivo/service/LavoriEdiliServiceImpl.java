package it.preventivo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.preventivo.entity.LavoriEdili;
import it.preventivo.repository.LavoriEdiliRepository;

@Service
public class LavoriEdiliServiceImpl implements LavoriEdiliService {

    @Autowired
    private LavoriEdiliRepository repository;

    @Override
    public List<LavoriEdili> findAll() {
        return repository.findAll();
    }

    @Override
    public LavoriEdili findById(long id) {
        Optional<LavoriEdili> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public LavoriEdili save(LavoriEdili lavoroEdile) {
        return repository.save(lavoroEdile);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
    
    // Metodo per trovare lavori per un insieme di ID
	@Override
	public Object findLavoriByIds(List<Long> idLavorazioni) {		
		return repository.findAllById(idLavorazioni);
	}

//	@Override
//	public Object findLavoriByIds(List<Long> idLavorazioni) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	/**
     * Trova i lavori edili basati su una lista di ID.
     * 
     * @param idLavorazioni Lista degli ID dei lavori da cercare.
     * @return Lista di lavori edili corrispondenti agli ID forniti.
     */
//    @Override
//    public List<LavoriEdili> findLavoriByIds(List<Long> idLavorazioni) {
//        return repository.findByIdIn(idLavorazioni);
//    }
}
