package it.nextdevs.gestioneEventi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.nextdevs.gestioneEventi.enums.RuoloUtente;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "utenti")
public class Utente implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RuoloUtente ruoloUtente;
    @OneToMany(mappedBy = "utente")
    @JsonIgnore
    private List<Partecipazione> partecipazioni;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruoloUtente.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
