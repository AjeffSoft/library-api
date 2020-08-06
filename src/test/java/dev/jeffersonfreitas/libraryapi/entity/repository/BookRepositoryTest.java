package dev.jeffersonfreitas.libraryapi.entity.repository;

import dev.jeffersonfreitas.libraryapi.model.entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    TestEntityManager manager;

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Deve retornar true se existir um livro com o mesmo Isbn cadastrado")
    public void returnTrueIfBookIsbnExists(){
        String isbn = "123";
        Book book = Book.builder().title("As aventuras").author("Sicreano").isbn(isbn).build();
        manager.persist(book);

        boolean result = repository.existsByIsbn(isbn);

        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("Deve retornar false se não existir um livro com o mesmo Isbn cadastrado")
    public void returnFalseIfBookIsbnDoesntExists(){
        String isbn = "123";
        boolean result = repository.existsByIsbn(isbn);
        assertThat(result).isFalse();
    }
}
