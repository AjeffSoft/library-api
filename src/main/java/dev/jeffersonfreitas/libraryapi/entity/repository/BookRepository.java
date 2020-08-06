package dev.jeffersonfreitas.libraryapi.entity.repository;

import dev.jeffersonfreitas.libraryapi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbn(String isbn);
}
