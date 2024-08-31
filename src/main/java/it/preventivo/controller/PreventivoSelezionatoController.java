package it.preventivo.controller;

import it.preventivo.entity.Preventivo;
import it.preventivo.entity.StatoPreventivo;
import it.preventivo.entity.Utente;
import it.preventivo.service.PreventivoService;
import it.preventivo.service.PreventivoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/preventiviSelezionato")

public class PreventivoSelezionatoController {

    private final PreventivoService preventivoServiceImpl;

    @Autowired
    public PreventivoSelezionatoController(PreventivoServiceImpl preventivoServiceImpl) {
        this.preventivoServiceImpl = preventivoServiceImpl;
    }
    
 // Endpoint per ottenere un preventivo per ID
    @GetMapping("/{id}")
    public String getPreventivoById(@PathVariable Long id, Model model) {
        Preventivo preventivo = preventivoServiceImpl.getPreventivoById(id);
        if (preventivo == null) {
            return "404"; // Nome della vista per errore 404
        }
        model.addAttribute("preventivo", preventivo);
        return "preventivo-detail"; // Nome del file Thymeleaf per il dettaglio
    }
    
 // Endpoint per salvare un nuovo preventivo
    @PostMapping
    public String savePreventivo(@ModelAttribute Preventivo preventivo) {
        preventivoServiceImpl.savePreventivo(preventivo);
        return "redirect:/preventivi"; // Redirect alla lista dei preventivi
    }
    
 // Endpoint per aggiornare un preventivo esistente
    @PutMapping("/{id}")
    public String updatePreventivo(@PathVariable Long id, @ModelAttribute Preventivo preventivo) {
        Preventivo updatedPreventivo = preventivoServiceImpl.updatePreventivo(id, preventivo);
        if (updatedPreventivo == null) {
            return "404"; // Nome della vista per errore 404
        }
        return "redirect:/preventivi"; // Redirect alla lista dei preventivi
    }
    
 // Endpoint per eliminare un preventivo
    @DeleteMapping("/{id}")
    public String deletePreventivo(@PathVariable Long id) {
        preventivoServiceImpl.deletePreventivo(id);
        return "redirect:/preventivi"; // Redirect alla lista dei preventivi
    }
    
 // Endpoint per cercare preventivi per utente
    @GetMapping("/utente")
    public String getPreventiviByUtente(@RequestParam Utente utente, Model model) {
        model.addAttribute("preventivi", preventivoServiceImpl.getPreventiviByUtente(utente));
        return "preventiviSelezionati"; // Nome del file Thymeleaf
    }
    
// // Endpoint per cercare preventivi per stato
//    @GetMapping("/stato")
//    public String getPreventiviByStato(@RequestParam StatoPreventivo stato, Model model) {
//        model.addAttribute("preventivi", preventivoServiceImpl.getPreventiviByStato(stato));
//        return "preventivi"; // Nome del file Thymeleaf
//    }
//    
    
 // Endpoint per cercare preventivi con totale maggiore di un valore
    @GetMapping("/totale/greater")
    public String getPreventiviByTotaleGreaterThan(@RequestParam double totale, Model model) {
        model.addAttribute("preventivi", preventivoServiceImpl.getPreventiviByTotaleGreaterThan(totale));
        return "preventiviSelezionati"; // Nome del file Thymeleaf
    }

    // Endpoint per cercare preventivi con totale minore di un valore
    @GetMapping("/totale/less")
    public String getPreventiviByTotaleLessThan(@RequestParam double totale, Model model) {
        model.addAttribute("preventivi", preventivoServiceImpl.getPreventiviByTotaleLessThan(totale));
        return "preventiviSelezionati"; // Nome del file Thymeleaf
    }
    
    
 // Endpoint per cercare preventivi per intervallo di date (LocalDate)
    @GetMapping("/date-range")
    public String getPreventiviByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {
        model.addAttribute("preventivi", preventivoServiceImpl.getPreventiviByDateRange(startDate, endDate));
        return "preventiviSelezionati"; // Nome del file Thymeleaf
    }

    // Endpoint per cercare preventivi per intervallo di date (LocalDateTime)
    @GetMapping("/datetime-range")
    public String findPreventiviByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model) {
        model.addAttribute("preventivi", preventivoServiceImpl.findPreventiviByDateRange(startDate, endDate));
        return "preventiviSelezionati"; // Nome del file Thymeleaf
    }

    // Endpoint per ordinare preventivi per totale discendente
    @GetMapping("/order/totale/desc")
    public String getPreventiviOrderByTotaleDesc(Model model) {
        model.addAttribute("preventivi", preventivoServiceImpl.getPreventiviOrderByTotaleDesc());
        return "preventiviSelezionati"; // Nome del file Thymeleaf
    }
    
    
    // Endpoint esistente per ottenere tutti i preventivi
    @GetMapping("/preventivi2")
    public String getAllPreventivi(Model model) {
        model.addAttribute("preventivi", preventivoServiceImpl.getAllPreventivi());
        return "preventiviSelezionati"; // Nome del file Thymeleaf (preventivi.html)
    }

    // Nuovo endpoint per cercare preventivi per data di creazione
    @GetMapping("/preventivi2/search")
    public String searchPreventiviByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            Model model) {
        List<Preventivo> preventivi = preventivoServiceImpl.findPreventiviByDateRange(startDate, endDate);
        model.addAttribute("preventivi", preventivi);
        return "preventiviSelezionati"; // Nome del file Thymeleaf (preventivi.html)
    }
}
