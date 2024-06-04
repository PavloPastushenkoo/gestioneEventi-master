package it.nextdevs.gestioneEventi.service;

import it.nextdevs.gestioneEventi.dto.UtenteLoginDto;
import it.nextdevs.gestioneEventi.exceptions.NonAutorizzatoException;
import it.nextdevs.gestioneEventi.model.Utente;
import it.nextdevs.gestioneEventi.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private JwtTool jwtTool;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUtenteAndCreateToken(UtenteLoginDto utenteLoginDto) {
        Optional<Utente> utenteOptional = utenteService.getUtenteByEmail(utenteLoginDto.getEmail());

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();

            if (passwordEncoder.matches(utenteLoginDto.getPassword(), utente.getPassword())) {
                return jwtTool.createToken(utente);
            } else {
                throw new NonAutorizzatoException("Password errata. Fai di nuovo il login!");
            }
        } else {
            throw new NonAutorizzatoException("Utente non presente. Fai di nuovo il login!");
        }
    }
}
