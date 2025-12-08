package com.boottech.springwebfluxcassandra.service.impl;

import com.boottech.springwebfluxcassandra.domain.Author;
import com.boottech.springwebfluxcassandra.exception.DataNotFoundException;
import com.boottech.springwebfluxcassandra.repository.AuthorRepository;
import com.boottech.springwebfluxcassandra.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Author> getAll() {
        return  repository.findAll();
    }

    @Override
    public Mono<Author> add(Author author) {
        log.info("add author : {} " , author );
        author.setId(UUID.randomUUID());
        return repository.save(author)
            .log();
    }

    @Override
    public Mono<Author> getById(String id) {
        return repository.findById(UUID.fromString(id))
            .switchIfEmpty(Mono.error(new DataNotFoundException("Author id not found")));
    }

    @Override
    public Mono<Author> update(Author author, String id) {
        return repository.findById(UUID.fromString(id))
            .flatMap(author1 -> {
                author1.setFirstname(author.getFirstname());
                author1.setLastname(author.getLastname());
                return repository.save(author1);
            });
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(UUID.fromString(id));
    }
}
