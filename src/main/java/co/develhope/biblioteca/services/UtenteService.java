package co.develhope.biblioteca.services;

import co.develhope.biblioteca.entities.Utente;
import co.develhope.biblioteca.enumerates.RecordStatusEnum;
import co.develhope.biblioteca.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    public Utente addUtente(Utente utente){
        return utenteRepository.save(utente);
    }
    public List<Utente> getAllUtente(){
        return utenteRepository.findAllActiveUtente();
    }
    public Optional<Utente> getUtenteById(Long id){
        return utenteRepository.findById(id);
    }
    public Optional<Utente> updateUtente(Utente utente, Long id){
        Optional<Utente> utenteOptional = utenteRepository.findById(id);
        if(utenteOptional.isPresent()){
            utenteOptional.get().setNome(utente.getNome());
            utenteOptional.get().setCognome(utente.getCognome());
            utenteOptional.get().setBooks(utente.getBooks());
            Utente utenteUpdated = utenteRepository.save(utenteOptional.get());
            return Optional.of(utenteUpdated);
        }else{
            return Optional.empty();
        }
    }
    public Optional<Utente> deleteUtente(Long id){
        Optional<Utente> utenteOptional = utenteRepository.findById(id);
        if (utenteOptional.isPresent()){
            utenteOptional.get().setRecordStatusEnum(RecordStatusEnum.I);
            utenteRepository.save(utenteOptional.get());
        }else {
            return Optional.empty();
        }
        return utenteOptional;
    }

    public List<Utente> getAllInactiveUtente(){
        return utenteRepository.findAllInactiveUtente();
    }
    public String checkMaxNumberOfBooksForUtente(Long idUtente){
        Optional<Utente> utente = utenteRepository.findById(idUtente);
        if (utente.isPresent()) {
            if(utente.get().getBooks().size() < 8 ){
                if (utente.get().getBooks().size() == 1){
                    Integer x =  8 - utente.get().getBooks().size();
                    return "L'utente ha " + utente.get().getBooks().size() + " libro quindi può prenotare ancora " + x + " libri";
                }
                Integer x =  8 - utente.get().getBooks().size();
                return "L'utente ha " + utente.get().getBooks().size() + " libri quindi può prenotare ancora " + x + " libri";
            }
            return "L'utente non può prenotare più libri ne ha già "+utente.get().getBooks().size();
        }
        return "Utente non trovato";
    }
}
