package it.preventivo.controller;

import it.preventivo.entity.Lavorazione;
import it.preventivo.entity.LavoriRestauro;
import it.preventivo.entity.Preventivo;
import it.preventivo.entity.Utente;
import it.preventivo.service.LavorazioneService;
import it.preventivo.service.LavoriRestauroService;
import it.preventivo.service.PreventivoService;
import it.preventivo.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PreventivoControllerNuovo {

    @Autowired
    private PreventivoService preventivoService;
    
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private LavorazioneService lavorazioneService;
    @Autowired
    private LavoriRestauroService lavoriRestauroService;

    // Metodo per visualizzare la pagina di creazione del preventivo
    @GetMapping("/preventiviNuovo/crea")
    public String showCreatePreventivoForm(Model model) {
        // Creare un nuovo oggetto Preventivo e passarlo al modello
        model.addAttribute("preventivo", new Preventivo());
        
        // Recupera tutti gli utenti per popolare il campo "utente" nel form
        List<Utente> utenti = utenteService.findAll();
        model.addAttribute("utenti", utenti);

        // Recupera tutte le lavorazioni per popolare il selettore multiplo
        List<Lavorazione> lavorazioni = lavorazioneService.getAllLavorazioni();
        model.addAttribute("lavorazioni", lavorazioni);
        System.out.println("Ho recuperato la lista delle lavorazioni");
        System.out.println("Stampo la lista delle lavorazioni"+lavorazioni);
        return "/createPreventivoNuovo"; // Nome del template Thymeleaf
    }

    @PostMapping("/preventiviNuovo/save")
    public String savePreventivo(@ModelAttribute("preventivo") Preventivo preventivo, 
                                 @RequestParam(value = "lavorazioni", required = false) List<Long> lavorazioneIds) {
        // Recupera le lavorazioni selezionate dal database
        List<Lavorazione> lavorazioniSelezionate = lavorazioneService.getLavorazioniByIds(lavorazioneIds);
        
        // Associa le lavorazioni al preventivo
        preventivo.setLavorazioni(lavorazioniSelezionate);

        // Salva il preventivo nel database
        preventivoService.savePreventivo(preventivo);

        // Debug: stampa un messaggio di conferma
        System.out.println("Preventivo salvato con successo!");
        System.out.println("Il preventivo è:"+preventivo);
        System.out.println("Le lavorazioni sono: "+lavorazioneIds);
        // Reindirizza alla lista dei preventivi
        return "redirect:/preventivi/list";
    }

    @PostMapping("/crea")
    public Preventivo creaPreventivo(
            @RequestParam Long idUtente,
            @RequestParam String tipoLavoro,
            @RequestBody List<Long> idsLavorazioni) {
        return preventivoService.creaPreventivo(idUtente, tipoLavoro, idsLavorazioni);
    }
    
}

