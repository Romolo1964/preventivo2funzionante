package it.preventivo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.preventivo.entity.LavoriTecnologici;
import it.preventivo.entity.Utente;
import it.preventivo.service.LavoriTecnologiciService;
import it.preventivo.service.UtenteService;

@Controller
@RequestMapping("/lavoriTecnologici")
public class LavoriTecnologiciController {

    @Autowired
    private LavoriTecnologiciService lavoriTecnologiciService;
    @Autowired
    private UtenteService utenteService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("lavoriTecnologiciList", lavoriTecnologiciService.findAll());
        
        List<Utente> utenti = utenteService.findAll();
        model.addAttribute("utente", new Utente());  // Assicurati che l'oggetto 'utente' sia presente nel modello
        return "lavoriTecnologici/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("lavoriTecnologici", new LavoriTecnologici());
        return "lavoriTecnologici/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute LavoriTecnologici lavoriTecnologici) {
        lavoriTecnologiciService.save(lavoriTecnologici);
        return "redirect:/lavoriTecnologici/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("lavoriTecnologici", lavoriTecnologiciService.findById(id).orElse(null));
        return "lavoriTecnologici/form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute LavoriTecnologici lavoriTecnologici) {
        lavoriTecnologiciService.save(lavoriTecnologici);
        return "redirect:/lavoriTecnologici/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        lavoriTecnologiciService.deleteById(id);
        return "redirect:/lavoriTecnologici/list";
    }
}

