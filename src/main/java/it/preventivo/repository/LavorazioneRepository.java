package it.preventivo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.preventivo.entity.Lavorazione;

@Repository
public interface LavorazioneRepository extends JpaRepository<Lavorazione, Long> {
    
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
}
