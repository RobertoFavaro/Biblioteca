package co.develhope.biblioteca.entities;

import co.develhope.biblioteca.enumerates.RecordStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "record_status", nullable = false, length = 1)
    @JsonIgnore
    private RecordStatusEnum recordStatusEnum = RecordStatusEnum.A;
    @OneToMany(mappedBy = "utente")
    @JsonIgnore
    private List<Book> books;
    public Utente(){}

    public Utente(Long id, String nome, String cognome, @NonNull RecordStatusEnum recordStatusEnum, List<Book> books) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.recordStatusEnum = recordStatusEnum;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @NonNull
    public RecordStatusEnum getRecordStatusEnum() {
        return recordStatusEnum;
    }

    public void setRecordStatusEnum(@NonNull RecordStatusEnum recordStatusEnum) {
        this.recordStatusEnum = recordStatusEnum;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
