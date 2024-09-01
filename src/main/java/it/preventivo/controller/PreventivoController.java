package it.preventivo.controller;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import it.preventivo.entity.Lavorazione;
import it.preventivo.entity.Preventivo;
import it.preventivo.entity.Utente;
import it.preventivo.repository.LavorazioneRepository;
import it.preventivo.repository.UtenteRepository;
import it.preventivo.service.LavoriEdiliService;
import it.preventivo.service.LavoriElettriciServiceImpl;
import it.preventivo.service.LavoriElettriciServiceImpl;
import it.preventivo.service.LavoriManutenzioneService;
import it.preventivo.service.LavoriRestauroService;
import it.preventivo.service.LavoriTecnologiciService;
import it.preventivo.service.PreventivoServiceNOOOO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
            @RequestParam(name="listaLavori", required = false) List<Long> listaLavori,
            @RequestParam Map<String, String> quantita, // Mappa per ottenere le quantità delle lavorazioni
            Model model) {

        // Log di debug per verificare gli input ricevuti
    	System.out.println("Appena entrato nel @PostMapping`/crea`");
        System.out.println("Il cliente id è = " + utenteId);
        System.out.println("Tipo di lavori è = " + tipoLavoro);
        System.out.println("ID Lavorazioni selezionati: " + idLavorazioni);
        
       // System.out.println("Lista di tutti i lavori: " + listaLavori);
       // System.out.println("Quantità ricevute: " + quantita);
        System.out.println("*************************************************************** ");
        // Mappa per gestire le quantità
        Map<Long, Integer> quantitaMap = new HashMap<>();
        
//     // Popola la mappa con le quantità
//        double count=1;
//        for (Entry<String, String> entry : quantita.entrySet()) {
//            String key = entry.getKey();
//            System.out.println("Appena entrato nel for per recuperare le chiavi");
//            System.out.println("Numero: "+count+"  Chiave: " + key+"="+entry.getValue());
//            if (key.startsWith("quantita[")) {
//            	System.out.println("sto dentro l' if");
//            	System.out.println("La Key = "+key);
//                // Estrai l'ID e la quantità dalla chiave
//                String idStr = key.substring(9, key.length() - 1); // Estrai l'ID
//               // System.out.println(idStr);
//                
//                try {
//                  //  Long id = Long.parseLong(idStr);
//                  // Integer qty = Integer.parseInt(entry.getValue());
//                    
//                  //  System.out.println(id + " " + "qty");
//                 //   quantitaMap.put(id, qty);
//                } catch (NumberFormatException e) {
//                    System.out.println("Errore nella conversione di ID o quantità: " + e.getMessage());
//                }
//            }
//            count=count+1;
//        }

        // Log della mappa delle quantità
       // System.out.println("Mappa delle quantità: " + quantitaMap);
        
        
        
        
        
        // Popola la mappa con le quantità
        // Recupera la quantità per ciascuna lavorazione selezionata
        
//     // Trasforma la mappa delle quantità da String a 
//        for (String key : quantita.keySet()) {
//            if (key.startsWith("quantita[")) {
//                String idStr = key.substring(9, key.length() - 1); // Estrai l'ID
//                Long id = Long.parseLong(idStr);
//                Integer qty = Integer.parseInt(quantita.get(key));
//                quantitaMap.put(id, qty);
//                System.out.println("La Mappa"+quantitaMap);
//            }
//        }
//        
//     // Otteniamo la lista delle chiavi
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Stampa delle chiavi della mappa delle quantità:");
        for (String key : quantita.keySet()) {
           // System.out.println(key);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------");
        List<String> listaChiavi = new ArrayList<>(quantita.keySet());
     // Stampiamo la lista delle chiavi
        System.out.println("Lista delle chiavi: " + listaChiavi);
        System.out.println("Numero delle chiavi: " +listaChiavi.size());
        System.out.println("idLavorazioni:"+idLavorazioni);
        
  
        
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        /**
         * Metodo per filtrare e recuperare le quantità selezionate da una mappa di parametri
         * provenienti dal form HTML. La funzione cerca chiavi che iniziano con "quantita["
         * e associa le quantità corrispondenti agli ID delle lavorazioni selezionate.
         *
         * @param quantita Una mappa di parametri contenente chiavi nel formato "quantita[<idLavorazione>]"
         *                 e i relativi valori delle quantità.
         * @param idLavorazioni Una lista di ID delle lavorazioni selezionate dall'utente.
         * @return Una mappa contenente le quantità selezionate, con l'ID della lavorazione come chiave
         *         e la quantità associata come valore.
         */
        // Filtra solo le quantità selezionate
        // Ritorna una mappa con le quantità selezionate, con l'ID della lavorazione come chiave
        // e la quantità associata come valore.
        // Mappa per memorizzare le quantità selezionate
        Map<Long, Integer> quantitaSelezionate = new HashMap<>();
        
        // Itera attraverso tutte le chiavi della mappa dei parametri
        for (String key : quantita.keySet()) {
        	// Verifica se la chiave inizia con "quantita[" e se l'ID della lavorazione selezionata è presente'"]"
            if (key.startsWith("quantita[")) {
            	// Estrae l'ID della lavorazione dalla chiave usando il parsing del numero
                Long idLavorazione = Long.parseLong(key.replace("quantita[", "").replace("]", ""));
                
                if (idLavorazioni.contains(idLavorazione)) {
                    // Recupera la quantità dalla mappa quantita usando la chiave corrente
                    Integer qty = Integer.parseInt(quantita.get(key));
                    
                    // Aggiunge la quantità alla mappa quantitaSelezionate, associandola all'ID della lavorazione'
                    // Aggiunge l'ID della lavorazione e la quantità alla mappa delle quantità selezionate
                    quantitaSelezionate.put(idLavorazione, qty);
                    
                    // Debug: stampa l'id della lavorazione e la quantità associata
                    System.out.println("IdLavorazione: " + idLavorazione + ", Quantità: " + qty);
                }
            } 
        }


        // Stampa le quantità selezionate per il debug funziona ma lo commentato
//        quantitaSelezionate.forEach((id, qty) -> System.out.println("IdLavorazione: " + id + ", Quantità: " + qty));

        // Esegui la logica di business necessaria qui...

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        
        
        
        // Recupera l'utente dal repository e aggiungilo al modello, gestendo l'errore se l'utente non viene trovato
        utenteRepository.findById(utenteId).ifPresentOrElse(
            utente -> model.addAttribute("utente", utente),
            () -> model.addAttribute("errore", "Utente non trovato.")
        );

        // Se l'utente non è presente (per esempio, se è stato aggiunto un errore), ritorna alla vista di selezione dell'utente
        if (model.containsAttribute("errore")) {
            return "seleziona_utente"; // Torna alla vista di selezione utente se l'utente non esiste
        }

//        // Verifica se la lista di lavorazioni è vuota o assente
//        if (idLavorazioni == null || idLavorazioni.isEmpty()) {
//            model.addAttribute("errore", "Nessuna lavorazione selezionata.");
//            
//      
//			//      Long utenteID=utenteRepository.getById(utenteId);
//            model.addAttribute("utente", utente.getNome(utenteRepository.getById(utenteId)));
//            model.addAttribute("tipoLavoro", tipoLavoro);
//            model.addAttribute("listaLavori", listaLavori);
//            System.out.println("Nessuna lavorazione selezionata");
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
