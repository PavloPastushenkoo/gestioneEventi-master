package it.nextdevs.gestioneEventi.dto;

import it.nextdevs.gestioneEventi.enums.RuoloUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteDto {
    @NotNull
    private String nome;
    @NotNull
    private String cognome;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
    private RuoloUtente ruoloUtente;

    public UtenteDto(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruoloUtente = RuoloUtente.UTENTE_NORMALE;
    }
}
