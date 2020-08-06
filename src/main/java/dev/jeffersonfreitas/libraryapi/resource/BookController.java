package dev.jeffersonfreitas.libraryapi.resource;

import dev.jeffersonfreitas.libraryapi.exceptions.ApiErrors;
import dev.jeffersonfreitas.libraryapi.exceptions.BusinessException;
import dev.jeffersonfreitas.libraryapi.service.BookService;
import dev.jeffersonfreitas.libraryapi.dto.BookDTO;
import dev.jeffersonfreitas.libraryapi.model.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService service;
    private ModelMapper modelMapper;

    public BookController(BookService service, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody @Valid BookDTO dto){
        Book entity = modelMapper.map(dto, Book.class);
        entity = service.save(entity);
        return modelMapper.map(entity, BookDTO.class);
    }



}
