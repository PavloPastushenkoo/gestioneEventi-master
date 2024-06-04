package it.nextdevs.gestioneEventi.repository;

import it.nextdevs.gestioneEventi.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Optional<Utente> findByEmail(String email);
}
