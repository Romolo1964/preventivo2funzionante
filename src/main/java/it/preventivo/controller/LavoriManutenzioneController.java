package it.preventivo.controller;

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

import it.preventivo.entity.LavoriManutenzione;
import it.preventivo.entity.Utente;
import it.preventivo.service.LavoriManutenzioneService;
import it.preventivo.service.UtenteService;

@Controller
@RequestMapping("/lavoriManutenzione")
public class LavoriManutenzioneController {

    @Autowired
    private LavoriManutenzioneService lavoriManutenzioneService;
    @Autowired
    private UtenteService utenteService;
    
    @GetMapping
    public String getAll(Model model) {
        List<LavoriManutenzione> lavoriManutenzioneList = lavoriManutenzioneService.findAll();
        model.addAttribute("lavoriManutenzioneList", lavoriManutenzioneList);
        
        List<Utente> utenti = utenteService.findAll();
        model.addAttribute("utente", new Utente());  // Assicurati che l'oggetto 'utente' sia presente nel modello
        
        return "lavoriManutenzione/list";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        Optional<LavoriManutenzione> lavoriManutenzione = lavoriManutenzioneService.findById(id);
        if (lavoriManutenzione.isPresent()) {
            model.addAttribute("lavoriManutenzione", lavoriManutenzione.get());
            return "lavoriManutenzione/detail";
        } else {
            return "redirect:/lavoriManutenzione";
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("lavoriManutenzione", new LavoriManutenzione());
        return "lavoriManutenzione/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute LavoriManutenzione lavoriManutenzione) {
        lavoriManutenzioneService.save(lavoriManutenzione);
        return "redirect:/lavoriManutenzione";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<LavoriManutenzione> lavoriManutenzione = lavoriManutenzioneService.findById(id);
        if (lavoriManutenzione.isPresent()) {
            model.addAttribute("lavoriManutenzione", lavoriManutenzione.get());
            return "lavoriManutenzione/form";
        } else {
            return "redirect:/lavoriManutenzione";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute LavoriManutenzione lavoriManutenzione) {
        if (lavoriManutenzioneService.findById(id).isPresent()) {
            lavoriManutenzione.setId(id);
            lavoriManutenzioneService.save(lavoriManutenzione);
            return "redirect:/lavoriManutenzione";
        } else {
            return "redirect:/lavoriManutenzione";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        if (lavoriManutenzioneService.findById(id).isPresent()) {
            lavoriManutenzioneService.deleteById(id);
        }
        return "redirect:/lavoriManutenzione";
    }
}

