package it.preventivo.controller;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.preventivo.entity.Lavorazione;
import it.preventivo.entity.Preventivo;
import it.preventivo.repository.LavorazioneRepository;
import it.preventivo.repository.UtenteRepository;
import it.preventivo.service.LavoriEdiliService;
import it.preventivo.service.LavoriElettriciServiceImpl;
import it.preventivo.service.LavoriElettriciServiceImpl;
import it.preventivo.service.LavoriManutenzioneService;
import it.preventivo.service.LavoriRestauroService;
import it.preventivo.service.LavoriTecnologiciService;
import it.preventivo.service.PreventivoServiceNOOOO;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/preventivo")
public class PreventivoController {

	public enum LavoroEdile {
	    LAVORI_EDILI("Lavori edili"),
	    LAVORI_ELETTRICI("Lavori elettrici"),
	    LAVORI_TECNOLOGICI("Lavori tecnologici"),
		LAVORI_MANUTENZIONE("Lavori manutenzione"),
		LAVORI_DI_RESTAURO("Lavori_di_restauro");
	    private final String descrizione;

	    LavoroEdile(String descrizione) {
	        this.descrizione = descrizione;
	    }

	    public String getDescrizione() {
	        return descrizione;
	    }
	}
	
    @Autowired
    private UtenteRepository utenteRepository;

//    @Autowired
//    private LavorazioneRepository lavorazioneRepository;
    
    @Autowired
    private LavoriEdiliService lavoriEdiliService;
    
    @Autowired
    private LavoriElettriciServiceImpl lavoriElettriciService;
    
    
    @Autowired    
    private LavoriManutenzioneService lavoriManutenzioneService;
    
    @Autowired    
    private LavoriTecnologiciService lavoriTecnologiciService;
    
    @Autowired    
    private LavoriRestauroService lavoriRestauroService;
    
    @Autowired
    private PreventivoServiceNOOOO preventivoService;
    
    @GetMapping("/selezionaUtente")
    public String selezionaUtente(Model model) {
        model.addAttribute("utenti", utenteRepository.findAll());
        model.addAttribute("tipiLavoro", Arrays.asList(LavoroEdile.values()));
        
        return "seleziona_utente";
    }

    @GetMapping("/crea")
    public String creaPreventivo(
            @RequestParam(required = true) Long utenteId,
            @RequestParam(required = true) String tipoLavoro, 
            Model model) {
        // Verifica che l'utenteId non sia nullo e che il tipoLavoro sia fornito'
        // Log per il debug dei parametri ricevuti
        System.out.println("Il clienteID è=" + utenteId);
        System.out.println("Il tipo di lavoro è=" + tipoLavoro);

        // Verifica che l'utenteId non sia nullo e che il tipoLavoro sia fornito
        if (utenteId == null || tipoLavoro == null || tipoLavoro.isEmpty()) {
            model.addAttribute("errore", "Seleziona sia un utente che un tipo di lavorazione.");
            // Ritorna alla vista di selezione con un messaggio di errore
            return "seleziona_utente"; // Modifica con il nome della tua vista di selezione se necessario
        }

        // Recupera l'utente dal repository e aggiungilo al modello
        utenteRepository.findById(utenteId).ifPresentOrElse(
            utente -> model.addAttribute("utente", utente),
            () -> model.addAttribute("errore", "Utente non trovato.")
        );        

        // Aggiungi il tipo di lavoro al modello per usarlo nella vista
        model.addAttribute("tipoLavoro", tipoLavoro);
        
        // Seleziona la lista giusta di lavori in base al tipoLavoro
        switch (tipoLavoro.toLowerCase()) {
            case "lavori_edili":
                model.addAttribute("lavori", lavoriEdiliService.findAll());
                break;
            case "lavori_elettrici":
                model.addAttribute("lavori", lavoriElettriciService.findAll());
                break;
            case "lavori_tecnologici":
                model.addAttribute("lavori", lavoriTecnologiciService.findAll());
                break;
            case "lavori_di_restauro":
	           	model.addAttribute("lavori", lavoriRestauroService.findAll());
	            break;
            case "lavori_manutenzione":
               	model.addAttribute("lavori", lavoriManutenzioneService.findAll());
                break;
            
            default:
                model.addAttribute("errore", "Tipo di lavoro non valido.");
                // Ritorna alla vista di selezione con un messaggio di errore
                return "seleziona_utente"; // Modifica con il nome della tua vista di selezione se necessario
        }
        
        
        // Recupera la lista dei lavori edili dal repository e aggiungila al modello
        // Aggiungi la lista dei lavori edili al modello
       // model.addAttribute("lavori", lavoriEdiliService.findAll());
        
        // Ritorna la vista crea_preventivo.html per creare il preventivo
        return "crea_preventivo";
    }


    /**
     * Gestisce la richiesta POST per salvare un preventivo.
     *
     * @param utenteId      ID dell'utente per il quale viene creato il preventivo.
     * @param tipoLavoro    Tipo di lavoro selezionato dall'utente (es. "edili", "elettrici").
     * @param idLavorazioni Lista degli ID delle lavorazioni selezionate dall'utente (può essere null o vuota se nessuna lavorazione è selezionata).
     * @param model         Oggetto Model per passare dati alla vista.
     * @return Nome della vista da visualizzare ("visualizza_preventivo" o "seleziona_utente" in caso di errore).
     */
    @PostMapping("/crea")
    public String salvaPreventivo(
            @RequestParam Long utenteId,
            @RequestParam String tipoLavoro,
            @RequestParam(name="idLavorazioni", required = false) List<Long> idLavorazioni,
            Model model) {

        // Log di debug per verificare gli input ricevuti
    	System.out.println("Appena entrato nel @PostMapping`/crea`");
        System.out.println("Il cliente id è = " + utenteId);
        System.out.println("Tipo di lavori è = " + tipoLavoro);
        System.out.println("ID Lavorazioni selezionati: " + idLavorazioni);

        // Recupera l'utente dal repository e aggiungilo al modello, gestendo l'errore se l'utente non viene trovato
        utenteRepository.findById(utenteId).ifPresentOrElse(
            utente -> model.addAttribute("utente", utente),
            () -> model.addAttribute("errore", "Utente non trovato.")
        );

        // Se l'utente non è presente (per esempio, se è stato aggiunto un errore), ritorna alla vista di selezione dell'utente
        if (model.containsAttribute("errore")) {
            return "seleziona_utente"; // Torna alla vista di selezione utente se l'utente non esiste
        }

        // Verifica se la lista di lavorazioni è vuota o assente
//        if (idLavorazioni == null || idLavorazioni[].isEmpty()) {
//            model.addAttribute("errore", "Nessuna lavorazione selezionata.");
//            return "crea_preventivo"; // Torna alla vista di creazione del preventivo se non sono state selezionate lavorazioni
//        }

        // Aggiungi gli attributi necessari al modello
        model.addAttribute("idLavorazioni", idLavorazioni);
        model.addAttribute("tipoLavoro", tipoLavoro);
        
        
     // Seleziona la lista giusta di lavori in base al tipoLavoro
        switch (tipoLavoro.toLowerCase()) {
            case "lavori_edili":
                model.addAttribute("lavori", lavoriEdiliService.findLavoriByIds(idLavorazioni));
                break;
            case "lavori_elettrici":
                model.addAttribute("lavori", lavoriElettriciService.findLavoriByIds(idLavorazioni));
                break;
            case "lavori_tecnologici":
                model.addAttribute("lavori", lavoriTecnologiciService.findLavoriByIds(idLavorazioni));
                break;
            case "lavori_di_restauro":
	           	model.addAttribute("lavori", lavoriRestauroService.findLavoriByIds(idLavorazioni));
	            break;
            case "lavori_manutenzione":
               	model.addAttribute("lavori", lavoriManutenzioneService.findLavoriByIds(idLavorazioni));
                break;
            
            default:
                model.addAttribute("errore", "Tipo di lavoro non valido.");
                // Ritorna alla vista di selezione con un messaggio di errore
                return "seleziona_utente"; // Modifica con il nome della tua vista di selezione se necessario
        }
        // Recupera i dettagli delle lavorazioni selezionate e aggiungili al modello
       //  List<Lavorazione> lavoriSelezionati = lavorazioneService.findLavoriByIds(idLavorazioni);
        //   model.addAttribute("lavoriSelezionati", lavoriSelezionati);

//        // Crea il preventivo e aggiungilo al modello
//        Preventivo preventivo = preventivoService.creaPreventivo(utenteId, idLavorazioni);
//        model.addAttribute("preventivo", preventivo);

        // Ritorna alla vista di visualizzazione del preventivo
        return "visualizza_preventivo";
    }

    @PostMapping("/accetta")
    public String accettaPreventivo(@RequestParam Long preventivoId, Model model) {
        Preventivo preventivo = preventivoService.accettaPreventivo(preventivoId);
        model.addAttribute("preventivo", preventivo);
        return "preventivo_accettato";
    }
    
}
