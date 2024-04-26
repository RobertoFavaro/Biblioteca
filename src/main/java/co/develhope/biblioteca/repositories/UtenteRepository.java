package co.develhope.biblioteca.repositories;

import co.develhope.biblioteca.entities.Book;
import co.develhope.biblioteca.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>{
    @Query(value="SELECT * FROM utente where utente.record_status = 'A'", nativeQuery = true)
    List<Utente> findAllActiveUtente();
    @Query(value="SELECT * FROM utente where utente.record_status = 'I'", nativeQuery = true)
    List<Utente> findAllInactiveUtente();
}
