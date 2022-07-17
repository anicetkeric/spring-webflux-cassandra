package com.boottech.springwebfluxcassandra.service;

import com.boottech.springwebfluxcassandra.domain.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {

    Flux<Author> getAll();

    Mono<Author> add(Author author);

    Mono<Author> getById(String id);

    Mono<Author> update(Author author, String id);

    Mono<Void> deleteById(String id);
}
