package co.develhope.biblioteca.controllers;

import co.develhope.biblioteca.entities.Book;
import co.develhope.biblioteca.services.BookService;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.security.Key;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/create")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book bookAdded = bookService.addBook(book);
        return ResponseEntity.ok().body(bookAdded);
    }
    @GetMapping("/readAll")
    public ResponseEntity<List<Book>> returnAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }
    @GetMapping("/read/{id}")
    public ResponseEntity<Book> returnBookById(@PathVariable Long id){
        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookOptional.get());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id){
        Optional<Book> bookOptional = bookService.updateBook(book, id);
        if (bookOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookOptional.get());
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Optional<Book> bookOptional = bookService.deleteBook(id);
        if (bookOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookOptional.get());
    }
    @PutMapping("/lend/{id}")
    public ResponseEntity<Book> lendBook(@PathVariable Long id) throws Exception {
        Optional<Book> bookOptional = bookService.lendBook(id);
        if (bookOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookOptional.get());
    }
    @PutMapping("/return/{id}")
    public ResponseEntity<Book> returnBook(@PathVariable Long id) throws Exception {
        Optional<Book> bookOptional = bookService.returnBook(id);
        if (bookOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookOptional.get());
    }
    @GetMapping("/readAllInactive")
    public ResponseEntity<List<Book>> returnAllInactiveBooks(){
        List<Book> books = bookService.getAllInactiveBooks();
        return ResponseEntity.ok().body(books);
    }
}
