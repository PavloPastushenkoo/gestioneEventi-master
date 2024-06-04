package it.nextdevs.gestioneEventi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "partecipazioni")
public class Partecipazione {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name="utente_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name="evento_id")
    private Evento evento;
}
