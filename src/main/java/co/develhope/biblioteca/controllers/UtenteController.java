package co.develhope.biblioteca.controllers;

import co.develhope.biblioteca.entities.Book;
import co.develhope.biblioteca.entities.Utente;
import co.develhope.biblioteca.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utente")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;
    @PostMapping("/create")
    public ResponseEntity<Utente> addUtente(@RequestBody Utente utente){
        Utente utenteAdded = utenteService.addUtente(utente);
        return ResponseEntity.ok().body(utenteAdded);
    }
    @GetMapping("/readAll")
    public ResponseEntity<List<Utente>> returnAllUtente(){
        List<Utente> utenteList = utenteService.getAllUtente();
        return ResponseEntity.ok().body(utenteList);
    }
    @GetMapping("/readSingle/{id}")
    public ResponseEntity<Utente> returnUtenteById(@PathVariable Long id){
        Optional<Utente> utenteView = utenteService.getUtenteById(id);
        if (utenteView.isPresent()){
            return ResponseEntity.ok().body(utenteView.get());
        }else return ResponseEntity.notFound().build();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Utente> updateUtente(@RequestBody Utente utente, @PathVariable Long id){
        Optional<Utente> utenteOptional = utenteService.updateUtente(utente, id);
        if(utenteOptional.isPresent()){
            return ResponseEntity.ok().body(utenteOptional.get());
        }else return ResponseEntity.notFound().build();
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Utente> deleteUtente(@PathVariable Long id){
        Optional<Utente> utenteOptional = utenteService.deleteUtente(id);
        if(utenteOptional.isPresent()){
            return ResponseEntity.ok().body(utenteOptional.get());
        }else return ResponseEntity.notFound().build();
    }
    @GetMapping("/readAllInactive")
    public ResponseEntity<List<Utente>> returnAllInactiveUtente(){
        List<Utente> utente = utenteService.getAllInactiveUtente();
        return ResponseEntity.ok().body(utente);
    }
    @GetMapping("/canUtenteTakeBook/{id}")
    public ResponseEntity<String> checkMaxNumberOfBooksForUtente(@PathVariable Long id){
        String prenotazione = utenteService.checkMaxNumberOfBooksForUtente(id);
        return ResponseEntity.ok().body(prenotazione);
    }
}
