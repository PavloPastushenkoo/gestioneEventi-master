package it.nextdevs.gestioneEventi.exceptions;

import it.nextdevs.gestioneEventi.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CentralizedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> BadRequestHandler(BadRequestException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> ExceptionHandler(Exception e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NonAutorizzatoException.class)
    public ResponseEntity<Object> NonAutorizzatoHandler(NonAutorizzatoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UtenteNonTrovatoException.class)
    public ResponseEntity<Object> UtenteNonTrovatoHandler(UtenteNonTrovatoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventoNonTrovatoException.class)
    public ResponseEntity<Object> EventoNonTrovatoHandler(EventoNonTrovatoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventoPienoException.class)
    public ResponseEntity<Object> EventoPienoHandler(EventoPienoException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EventoPresenteException.class)
    public ResponseEntity<Object> EventoPresenteHandler(EventoPresenteException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PartecipazionePresenteException.class)
    public ResponseEntity<Object> PartecipazionePresenteHandler(PartecipazionePresenteException e) {
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setDataErrore(LocalDateTime.now());
        error.setStatoErrore(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
