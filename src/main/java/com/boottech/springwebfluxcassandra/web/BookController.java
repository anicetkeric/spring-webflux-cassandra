package com.boottech.springwebfluxcassandra.web;

import com.boottech.springwebfluxcassandra.domain.Book;
import com.boottech.springwebfluxcassandra.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public Flux<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/book/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable("id") String id) {

        return bookService.getById(id)
            .map(book1 -> ResponseEntity.ok()
                .body(book1))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
            .log();
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> addBook(@RequestBody @Valid Book book) {
        return bookService.add(book);

    }

    @PutMapping("/book/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Book>> updateBook(@RequestBody Book book, @PathVariable String id) {

        var updatedBookMono =  bookService.update(book, id);
        return updatedBookMono
            .map(book1 -> ResponseEntity.ok()
                .body(book1))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @DeleteMapping("/book/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteBookById(@PathVariable String id){
        return bookService.deleteById(id);
    }

}
