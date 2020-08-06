package dev.jeffersonfreitas.libraryapi.service.impl;

import dev.jeffersonfreitas.libraryapi.entity.repository.BookRepository;
import dev.jeffersonfreitas.libraryapi.exceptions.BusinessException;
import dev.jeffersonfreitas.libraryapi.model.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements dev.jeffersonfreitas.libraryapi.service.BookService {

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book book) {
        if(repository.existsByIsbn(book.getIsbn())){
            throw new BusinessException("Isbn já cadastrado!");
        }
        return repository.save(book);
    }
}
