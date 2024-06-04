package it.nextdevs.gestioneEventi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDto {
    @NotNull
    private String titolo;
    @NotNull
    private String descrizione;
    @NotNull
    private LocalDate data;
    @NotNull
    private String luogo;
    @NotNull
    private Integer numPostiDisponibili;

    public EventoDto(String titolo, String descrizione, LocalDate data, String luogo, Integer numPostiDisponibili) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numPostiDisponibili = numPostiDisponibili;
    }
}
