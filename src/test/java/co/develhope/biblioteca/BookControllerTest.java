package co.develhope.biblioteca;

import co.develhope.biblioteca.controllers.BookController;
import co.develhope.biblioteca.entities.Book;
import co.develhope.biblioteca.entities.Utente;
import co.develhope.biblioteca.enumerates.RecordStatusEnum;
import co.develhope.biblioteca.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookControllerTest {
    @Autowired
    private BookController bookController;
    @Autowired
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void contextLoads(){
        assertThat(bookController).isNotNull();
    }

    @Test
    @Order(1)
    void createBook()throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitolo("Franceschini");
        book.setAutore("Francesco Papullo");
        book.setAnnoDiPubblicazione(1999);
        book.setDisponibilita(true);

        String bookJSON = objectMapper.writeValueAsString(book);

        MvcResult resultActions = this.mockMvc.perform(post("/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @Order(3)
    void getAllBook() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/book/readAll"))
                .andDo(print()).andReturn();

        List<Book> userFromResponseList = objectMapper.readValue(result.getResponse().getContentAsString(),
                List.class);
        assertThat(userFromResponseList.size()).isNotZero();
    }

    @Test
    @Order(4)
    void getByBookId() throws Exception {
        Long bookId = 1L;

        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/book/read/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookId)).andReturn();
    }

    @Test
    @Order(2)
    void updateBook() throws Exception {
        Long bookId = 1L;
        Utente utente = new Utente();
        Book updateBook = new Book(bookId, "Franceschini", "Francesco Papullo", 1999, true, RecordStatusEnum.A, utente);
        String bookJSON = objectMapper.writeValueAsString(updateBook);

        MvcResult resultUpdate = this.mockMvc.perform(MockMvcRequestBuilders.put("/book/update/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON).content(bookJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = resultUpdate.getResponse().getContentAsString();
        Assertions.assertNotNull(content);

    }


    @Test
    @Order(5)
    void deleteBook() throws Exception {
        Long bookId = 1L;

        MvcResult result = mockMvc.perform(delete("/book/delete/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }
}