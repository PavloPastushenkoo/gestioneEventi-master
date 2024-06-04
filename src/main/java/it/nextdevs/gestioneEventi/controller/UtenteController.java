package it.nextdevs.gestioneEventi.controller;

import it.nextdevs.gestioneEventi.model.Partecipazione;
import it.nextdevs.gestioneEventi.model.Utente;
import it.nextdevs.gestioneEventi.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public Page<Utente> getAllUtenti(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "15") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return utenteService.getAllUtenti(page, size, sortBy);
    }

    @PostMapping("/{id}/partecipa/{evento}")
    public Partecipazione partecipaEvento(@PathVariable Integer id, @PathVariable Integer evento) {
        return utenteService.partecipaEvento(evento, id);
    }
}
