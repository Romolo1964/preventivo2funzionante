package it.preventivo.service;

import java.util.List;
import it.preventivo.entity.Lavorazione;

public interface LavorazioneService {

    /**
     * Salva una nuova lavorazione o aggiorna una esistente.
     *
     * @param lavorazione La lavorazione da salvare o aggiornare.
     * @return La lavorazione salvata o aggiornata.
     */
    Lavorazione saveLavorazione(Lavorazione lavorazione);

    /**
     * Ottiene una lavorazione per ID.
     *
     * @param id L'ID della lavorazione.
     * @return La lavorazione trovata, o null se non esiste.
     */
    Lavorazione getLavorazioneById(Long id);

    /**
     * Ottiene tutte le lavorazioni.
     *
     * @return Una lista di tutte le lavorazioni.
     */
    List<Lavorazione> getAllLavorazioni();

    /**
     * Cancella una lavorazione per ID.
     *
     * @param id L'ID della lavorazione da cancellare.
     */
    void deleteLavorazione(Long id);

    /**
     * Trova tutte le lavorazioni con una descrizione specifica.
     *
     * @param descrizione La descrizione da cercare.
     * @return Una lista di lavorazioni che corrispondono alla descrizione fornita.
     */
    List<Lavorazione> findByDescrizione(String descrizione);

    /**
     * Trova tutte le lavorazioni con un costo maggiore di quello specificato.
     *
     * @param costo Il costo minimo.
     * @return Una lista di lavorazioni con costo maggiore di quello fornito.
     */
    List<Lavorazione> findByCostoGreaterThan(double costo);

    /**
     * Trova tutte le lavorazioni con un costo minore di quello specificato.
     *
     * @param costo Il costo massimo.
     * @return Una lista di lavorazioni con costo minore di quello fornito.
     */
    List<Lavorazione> findByCostoLessThan(double costo);

	List<Lavorazione> getLavorazioniByIds(List<Long> lavorazioneIds);
}
