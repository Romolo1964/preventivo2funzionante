package it.preventivo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.preventivo.entity.Utente;
import it.preventivo.service.UtenteService;

//@RestController
//@RequestMapping("/api/utenti")
public class UtenteControllerRest {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<Utente> getAllUtenti() {
        return utenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable long id) {
        Optional<Utente> utente = utenteService.findById(id);
        return utente.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente) {
        Utente savedUtente = utenteService.save(utente);
        return new ResponseEntity<>(savedUtente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utente> updateUtente(@PathVariable long id, @RequestBody Utente utenteDetails) {
        Optional<Utente> optionalUtente = utenteService.findById(id);
        if (optionalUtente.isPresent()) {
            Utente utente = optionalUtente.get();
            utente.setCategoriaLavoriId(utenteDetails.getCategoriaLavoriId());
            utente.setCognome(utenteDetails.getCognome());
            utente.setCreatoIl(utenteDetails.getCreatoIl());
            utente.setDataDiNascita(utenteDetails.getDataDiNascita());
            utente.setEmail(utenteDetails.getEmail());
            utente.setModificatoIl(utenteDetails.getModificatoIl());
            utente.setNome(utenteDetails.getNome());
            utente.setPassword(utenteDetails.getPassword());
            Utente updatedUtente = utenteService.save(utente);
            return ResponseEntity.ok(updatedUtente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable long id) {
        utenteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
