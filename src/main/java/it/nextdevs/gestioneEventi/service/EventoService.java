package it.nextdevs.gestioneEventi.service;

import it.nextdevs.gestioneEventi.dto.EventoDto;
import it.nextdevs.gestioneEventi.exceptions.EventoNonTrovatoException;
import it.nextdevs.gestioneEventi.exceptions.EventoPresenteException;
import it.nextdevs.gestioneEventi.model.Evento;
import it.nextdevs.gestioneEventi.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Page<Evento> getAllEventi(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return eventoRepository.findAll(pageable);
    }

    public Optional<Evento> getEventoById(Integer id) {
        return eventoRepository.findById(id);
    }

    public Optional<Evento> getEventoByLuogoAndData(String luogo, LocalDate data) {
        return eventoRepository.findByLuogoAndData(luogo, data);
    }

    public String saveEvento(EventoDto eventoDto) {
        Optional<Evento> eventoOptional = getEventoByLuogoAndData(eventoDto.getLuogo(), eventoDto.getData());
        if (eventoOptional.isEmpty()) {
            Evento evento = new Evento();
            evento.setData(eventoDto.getData());
            evento.setDescrizione(eventoDto.getDescrizione());
            evento.setTitolo(eventoDto.getTitolo());
            evento.setLuogo(eventoDto.getLuogo());
            evento.setNumPostiDisponibili(eventoDto.getNumPostiDisponibili());
            eventoRepository.save(evento);
            return "Evento con id "+evento.getId()+" creato con successo";
        } else {
            throw new EventoPresenteException("Evento presente per giorno e luogo indicati!");
        }
    }

    public Evento updateEvento(Integer id, EventoDto eventoDto) {
        Optional<Evento> eventoOptional = getEventoById(id);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            evento.setData(eventoDto.getData());
            evento.setDescrizione(eventoDto.getDescrizione());
            evento.setTitolo(eventoDto.getTitolo());
            evento.setLuogo(eventoDto.getLuogo());
            evento.setNumPostiDisponibili(eventoDto.getNumPostiDisponibili());
            return eventoRepository.save(evento);
        } else {
            throw new EventoNonTrovatoException("Evento con id "+id+" non trovato");
        }
    }

    public String deleteEvento(Integer id) {
        Optional<Evento> eventoOptional = getEventoById(id);
        if (eventoOptional.isPresent()) {
            eventoRepository.delete(eventoOptional.get());
            return "Evento con id "+id+" eliminato con successo";
        } else {
            throw new EventoNonTrovatoException("Evento con id "+id+" non trovato");
        }
    }
}
