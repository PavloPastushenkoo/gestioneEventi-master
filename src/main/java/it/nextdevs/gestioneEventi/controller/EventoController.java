package it.nextdevs.gestioneEventi.controller;

import it.nextdevs.gestioneEventi.dto.EventoDto;
import it.nextdevs.gestioneEventi.exceptions.BadRequestException;
import it.nextdevs.gestioneEventi.exceptions.EventoNonTrovatoException;
import it.nextdevs.gestioneEventi.model.Evento;
import it.nextdevs.gestioneEventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public Page<Evento> getAllEventi(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "15") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return eventoService.getAllEventi(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Evento getEventoById(@PathVariable Integer id) {
        Optional<Evento> eventoOptional = eventoService.getEventoById(id);
        if (eventoOptional.isPresent()) {
            return eventoOptional.get();
        } else {
            throw new EventoNonTrovatoException("Evento con id "+id+" non trovato");
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE_DI_EVENTI')")
    public String saveEvento(@RequestBody @Validated EventoDto eventoDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(DefaultMessageSourceResolvable::getDefaultMessage).
                    reduce("", (s,s2)->s+s2));
        }

        return eventoService.saveEvento(eventoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Evento updateEvento(@PathVariable Integer id,
                               @RequestBody @Validated EventoDto eventoDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().
                    map(DefaultMessageSourceResolvable::getDefaultMessage).
                    reduce("", (s,s2)->s+s2));
        }

        return eventoService.updateEvento(id, eventoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE_DI_EVENTI')")
    public String deleteEvento(@PathVariable Integer id) {
        return eventoService.deleteEvento(id);
    }
}
