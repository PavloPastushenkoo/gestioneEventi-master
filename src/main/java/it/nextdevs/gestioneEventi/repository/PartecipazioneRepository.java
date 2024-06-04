package it.nextdevs.gestioneEventi.repository;

import it.nextdevs.gestioneEventi.model.Evento;
import it.nextdevs.gestioneEventi.model.Partecipazione;
import it.nextdevs.gestioneEventi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartecipazioneRepository extends JpaRepository<Partecipazione, Integer> {
    Optional<Partecipazione> findByUtenteAndEvento(Utente utente, Evento evento);
}
