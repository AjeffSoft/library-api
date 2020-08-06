package dev.jeffersonfreitas.libraryapi.service;

import dev.jeffersonfreitas.libraryapi.entity.repository.BookRepository;
import dev.jeffersonfreitas.libraryapi.exceptions.BusinessException;
import dev.jeffersonfreitas.libraryapi.model.entity.Book;
import dev.jeffersonfreitas.libraryapi.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    BookService service;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new BookServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um livro valido")
    public void createBookTest() {
        Book book = newBook();
        Book savedBook = Book.builder().id(1l).isbn("123").author("Autor").title("As aventuras").build();
        Mockito.when(repository.existsByIsbn(Mockito.anyString())).thenReturn(false);
        Mockito.when(repository.save(book)).thenReturn(savedBook);
        Book result = service.save(book);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getIsbn()).isEqualTo("123");
        assertThat(result.getAuthor()).isEqualTo("Autor");
        assertThat(result.getTitle()).isEqualTo("As aventuras");
    }

    @Test
    @DisplayName("Deve retornar erro quando tentar salvar um book com o Isbn já cadastrado")
    public void shouldNotSaveABookWithDuplicatedIsbn() {
        Book book = newBook();
        Mockito.when(repository.existsByIsbn(Mockito.anyString())).thenReturn(true);

        Throwable result = Assertions.catchThrowable(() -> service.save(book));

        Assertions.assertThat(result).isInstanceOf(BusinessException.class).hasMessage("Isbn já cadastrado!");
        Mockito.verify(repository, Mockito.never()).save(book);
    }


    private Book newBook() {
        return Book.builder().isbn("123").author("Autor").title("As aventuras").build();
    }

}
