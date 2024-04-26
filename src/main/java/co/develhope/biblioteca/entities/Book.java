package co.develhope.biblioteca.entities;

import co.develhope.biblioteca.enumerates.RecordStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titolo;
    private String autore;
    private Integer annoDiPubblicazione;
    private boolean disponibilita;
    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "record_status", nullable = false, length = 1)
    @JsonIgnore
    private RecordStatusEnum recordStatusEnum = RecordStatusEnum.A;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    public Book(){}
    public Book(Long id, String titolo, String autore, Integer annoDiPubblicazione, boolean disponibilita, @NonNull RecordStatusEnum recordStatusEnum, Utente utente) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.disponibilita = disponibilita;
        this.recordStatusEnum = recordStatusEnum;
        this.utente = utente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Integer getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(Integer annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public boolean getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    @NonNull
    public RecordStatusEnum getRecordStatusEnum() {
        return recordStatusEnum;
    }

    public void setRecordStatusEnum(@NonNull RecordStatusEnum recordStatusEnum) {
        this.recordStatusEnum = recordStatusEnum;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
