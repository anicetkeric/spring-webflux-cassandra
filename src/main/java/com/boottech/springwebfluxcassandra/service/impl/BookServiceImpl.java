package com.boottech.springwebfluxcassandra.service.impl;

import com.boottech.springwebfluxcassandra.domain.Book;
import com.boottech.springwebfluxcassandra.exception.DataNotFoundException;
import com.boottech.springwebfluxcassandra.repository.BookRepository;
import com.boottech.springwebfluxcassandra.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Book> getAll() {
        return  repository.findAll();
    }

    @Override
    public Mono<Book> add(Book book) {
        book.setId(UUID.randomUUID());
        log.info("addBook : {} " , book );
        return repository.save(book)
            .log();
    }

    @Override
    public Mono<Book> getById(String id) {
        return repository.findById(UUID.fromString(id))
            .switchIfEmpty(Mono.error(new DataNotFoundException("Book id not found")));
    }

    @Override
    public Mono<Book> update(Book book, String id) {
        return repository.findById(UUID.fromString(id))
            .flatMap(book1 -> {
                book1.setTitle(book.getTitle());
                book1.setIsbn(book.getIsbn());
                book1.setDescription(book.getDescription());
                book1.setLanguage(book.getLanguage());
                book1.setPage(book.getPage());
                book1.setPrice(book.getPrice());
                book1.setPublication(book.getPublication());
                return repository.save(book1);
            });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(UUID.fromString(id));
    }
}
