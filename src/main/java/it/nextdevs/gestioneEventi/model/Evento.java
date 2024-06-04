package it.nextdevs.gestioneEventi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    private Integer id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private Integer numPostiDisponibili;
    @OneToMany(mappedBy = "evento")
    @JsonIgnore
    private List<Partecipazione> partecipazioni;
}
