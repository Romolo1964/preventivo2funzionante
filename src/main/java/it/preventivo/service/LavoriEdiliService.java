package it.preventivo.service;

import java.util.List;

import it.preventivo.entity.LavoriEdili;

public interface LavoriEdiliService {
    List<LavoriEdili> findAll();
    LavoriEdili findById(long id);
    LavoriEdili save(LavoriEdili lavoroEdile);
    void deleteById(long id);
	Object findLavoriByIds(List<Long> idLavorazioni);
	
	
	
}

