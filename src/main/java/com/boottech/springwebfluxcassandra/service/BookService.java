package com.boottech.springwebfluxcassandra.service;

import com.boottech.springwebfluxcassandra.domain.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<Book> getAll();

    Mono<Book> add(Book book);

    Mono<Book> getById(String id);

    Mono<Book> update(Book book, String id);

    Mono<Void> deleteById(String id);
}
