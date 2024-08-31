package it.preventivo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.preventivo.entity.Utente;
import it.preventivo.service.UtenteService;

@Controller
@RequestMapping("/utenti")
public class UtenteControllerThymeleaf {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getAllUtenti(Model model) {
        List<Utente> utenti = utenteService.findAll();
        model.addAttribute("utente", new Utente());  // Assicurati che l'oggetto 'utente' sia presente nel modello
        model.addAttribute("utenti", utenti);
        return "utenti";
    }

    @GetMapping("/new")
    public String showNewUtenteForm(Model model) {
        model.addAttribute("utente", new Utente());
        return "new_utente";
    }
//    @PostMapping("/submit")
//    public String submitForm(@ModelAttribute Utente utente, Model model) {
//        // Trova l'utente selezionato per ID
//        Optional<Utente> selectedUtente = utenteService.findById(utente.getIdutente());
//
//        if (selectedUtente.isPresent()) {
//        	System.out.println("Sono entrato nell' if del utenteController e precisamente nell :/submit;");
//        	System.out.println("STAMPO L'UTENTE  >>>>>>"+selectedUtente);
//            // Aggiunge l'utente selezionato al modello
//            model.addAttribute("selectedUtente", selectedUtente.get());
//            // Restituisce la vista 'utenteDettaglio.html'
//            return "utenteDettaglio";
//        } else {
//            // Se l'utente non viene trovato, puoi gestire il caso di errore
//            model.addAttribute("error", "Utente non trovato");
//            return "error";
//        }
//    }
    @PostMapping
    public String saveUtente(@ModelAttribute("utente") Utente utente) {
    	utente.setCreatoIl(new Date());  // Imposta la data corrente per il campo creatoIl
        utenteService.save(utente);
        return "redirect:/utenti";
    }

    @GetMapping("/edit/{id}")
    public String showEditUtenteForm(@PathVariable("id") long id, Model model) {
        Optional<Utente> utente = utenteService.findById(id);
        if (utente.isPresent()) {
            model.addAttribute("utente", utente.get());
            return "edit_utente";
        } else {
            return "redirect:/utenti";
        }
    }

    @PostMapping("/update/{id}")
    public String updateUtente(@PathVariable("id") long id, @ModelAttribute("utente") Utente utenteDetails) {
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
            utente.setModificatoIl(new Date());  // Imposta la data corrente per il campo modificatoIl
            utenteService.save(utente);
        }
        return "redirect:/utenti";
    }

    @GetMapping("/delete/{id}")
    public String deleteUtente(@PathVariable("id") long id) {
        utenteService.deleteById(id);
        return "redirect:/utenti";
    }
    // Nuovo metodo per visualizzare i dettagli dell'utente
    @GetMapping("/{id}")
    public String viewUtenteDetails(@PathVariable("id") long id, Model model) {
        Optional<Utente> utente = utenteService.findById(id);
        if (utente.isPresent()) {
            model.addAttribute("utente", utente.get());
            return "utente_dettaglio";
        } else {
            return "redirect:/utenti";
        }
    }
}

