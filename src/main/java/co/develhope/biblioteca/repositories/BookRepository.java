package co.develhope.biblioteca.repositories;

import co.develhope.biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value="SELECT * FROM book where book.record_status = 'A'", nativeQuery = true)
    List<Book> findAllActiveBook();
    @Query(value="SELECT * FROM book where book.record_status = 'I'", nativeQuery = true)
    List<Book> findAllInactiveBook();
}
