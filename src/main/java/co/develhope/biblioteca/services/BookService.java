package co.develhope.biblioteca.services;

import co.develhope.biblioteca.entities.Book;
import co.develhope.biblioteca.enumerates.RecordStatusEnum;
import co.develhope.biblioteca.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Book addBook(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return bookRepository.findAllActiveBook();
    }
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }
    public Optional<Book> updateBook(Book book, Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            bookOptional.get().setAutore(book.getAutore());
            bookOptional.get().setTitolo(book.getTitolo());
            bookOptional.get().setAnnoDiPubblicazione(book.getAnnoDiPubblicazione());
            bookOptional.get().setDisponibilita(book.getDisponibilita());
            bookOptional.get().setUtente(book.getUtente());
            Book bookUpdated = bookRepository.save(bookOptional.get());
            return Optional.of(bookUpdated);
        }else{
            return Optional.empty();
        }
    }
    public Optional<Book> deleteBook(Long id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            bookOptional.get().setRecordStatusEnum(RecordStatusEnum.I);
            bookRepository.save(bookOptional.get());
        }else {
            return Optional.empty();
        }
        return bookOptional;
    }
    public Optional<Book> lendBook(Long id) throws Exception {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            if (bookOptional.get().getDisponibilita()){
                bookOptional.get().setDisponibilita(false);
                bookRepository.save(bookOptional.get());
            }else {
                throw new Exception("Libro non disponibile");
            }
        }else {
            return Optional.empty();
        }
        return bookOptional;
    }
    public Optional<Book> returnBook(Long id) throws Exception {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            if (!bookOptional.get().getDisponibilita()){
                bookOptional.get().setDisponibilita(true);
                bookRepository.save(bookOptional.get());
            }else {
                throw new Exception("Libro già ritornato");
            }
        }else {
            return Optional.empty();
        }
        return bookOptional;
    }
    public List<Book> getAllInactiveBooks(){
        return bookRepository.findAllInactiveBook();
    }

}
