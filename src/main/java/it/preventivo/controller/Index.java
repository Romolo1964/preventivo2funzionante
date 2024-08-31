package it.preventivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.preventivo.entity.Utente;
import it.preventivo.service.UtenteService;

@Controller
public class Index {
	 @Autowired
	    private UtenteService utenteService;

    @RequestMapping("/")
    public String home(Model model){
    	model.addAttribute("utenti", utenteService.findAll());
        model.addAttribute("utente", new Utente());
        return "index";
    }
    }