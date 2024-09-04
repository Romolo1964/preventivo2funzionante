package it.preventivo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.preventivo.entity.Lavorazione;
import it.preventivo.service.LavorazioneService;

@Controller
@RequestMapping("/lavorazioni")
public class LavorazioneController {

    @Autowired
    private LavorazioneService lavorazioneService;

    /**
     * Mostra tutte le lavorazioni.
     *
     * @param model Il modello per aggiungere attributi alla vista.
     * @return Il nome della vista per mostrare tutte le lavorazioni.
     */
    @GetMapping
    public String getAllLavorazioni(Model model) {
        List<Lavorazione> lavorazioni = lavorazioneService.getAllLavorazioni();
        model.addAttribute("lavorazioni", lavorazioni);
        return "/listLavorazioni"; // Nome della vista (es. src/main/resources/templates/lavorazioni/list.html)
    }

    /**
     * Mostra il form per creare una nuova lavorazione.
     *
     * @param model Il modello per aggiungere attributi alla vista.
     * @return Il nome della vista per il form di creazione.
     */
    @GetMapping("/crea")
    public String showCreateForm(Model model) {
        model.addAttribute("lavorazione", new Lavorazione());
        return "/createLavorazioni"; // Nome della vista (es. src/main/resources/templates/lavorazioni/create.html)
    }

    /**
     * Gestisce la creazione di una nuova lavorazione.
     *
     * @param lavorazione La lavorazione da creare.
     * @return Reindirizza alla lista delle lavorazioni.
     */
    @PostMapping("/crea")
    public String creaLavorazione(@ModelAttribute Lavorazione lavorazione) {
        lavorazioneService.saveLavorazione(lavorazione);
        return "redirect:/lavorazioni"; // Reindirizza alla vista che mostra tutte le lavorazioni
    }

    /**
     * Mostra il form per aggiornare una lavorazione esistente.
     *
     * @param id    L'ID della lavorazione da aggiornare.
     * @param model Il modello per aggiungere attributi alla vista.
     * @return Il nome della vista per il form di aggiornamento.
     */
    @GetMapping("/modifica/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Lavorazione lavorazione = lavorazioneService.getLavorazioneById(id);
        System.out.println("L' ID della lavorazione : " + id);
        System.out.println("Il model è : " + model);
        if (lavorazione != null) {
            model.addAttribute("lavorazione", lavorazione);
            return "/updateLavorazioni"; // Nome della vista (es. src/main/resources/templates/lavorazioni/update.html)
        } else {
            return "redirect:/lavorazioni"; // Se la lavorazione non esiste, reindirizza alla lista
        }
    }

    /**
     * Gestisce l'aggiornamento di una lavorazione esistente.
     *
     * @param id          L'ID della lavorazione da aggiornare.
     * @param lavorazione La nuova lavorazione da salvare.
     * @return Reindirizza alla lista delle lavorazioni.
     */
    @PostMapping("/modifica/{id}")
    public String aggiornaLavorazione(@PathVariable Long id, @ModelAttribute Lavorazione lavorazione) {
        lavorazione.setId(id); // Assicura che l'ID sia settato per l'aggiornamento
        lavorazioneService.saveLavorazione(lavorazione);
        return "redirect:/lavorazioni"; // Reindirizza alla vista che mostra tutte le lavorazioni
    }

    /**
     * Gestisce la cancellazione di una lavorazione.
     *
     * @param id L'ID della lavorazione da cancellare.
     * @return Reindirizza alla lista delle lavorazioni.
     */
    @GetMapping("/cancella/{id}")
    public String cancellaLavorazione(@PathVariable Long id) {
        lavorazioneService.deleteLavorazione(id);
        return "redirect:/lavorazioni"; // Reindirizza alla vista che mostra tutte le lavorazioni
    }

    /**
     * Mostra tutte le lavorazioni con una descrizione specifica.
     *
     * @param descrizione La descrizione da cercare.
     * @param model       Il modello per aggiungere attributi alla vista.
     * @return Il nome della vista per mostrare le lavorazioni filtrate.
     */
    @GetMapping("/descrizione/{descrizione}")
    public String getLavorazioniByDescrizione(@PathVariable String descrizione, Model model) {
        List<Lavorazione> lavorazioni = lavorazioneService.findByDescrizione(descrizione);
        model.addAttribute("lavorazioni", lavorazioni);
        return "lavorazioni/list"; // Nome della vista per mostrare le lavorazioni filtrate
    }
}
