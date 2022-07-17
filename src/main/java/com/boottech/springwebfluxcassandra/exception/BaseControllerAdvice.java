package com.boottech.springwebfluxcassandra.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class BaseControllerAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<String> handleRequestBodyError(WebExchangeBindException ex, ServerWebExchange exchange){
        log.error("Exception caught in handleRequestBodyError :  {} " ,ex.getMessage(),  ex);
        var error = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .sorted()
                .collect(Collectors.joining(","));
        log.error("errorList : {}", error);
        return Mono.just(error);
    }

    @ExceptionHandler({Exception.class, ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<String> handleAllExceptions(Exception ex, ServerWebExchange exchange) {
        log.debug(ex.getMessage(), ex.getCause());
        return Mono.just(ex.getMessage());
    }

    @ExceptionHandler({DuplicateKeyException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<String> handleBadRequestException(Exception ex, ServerWebExchange exchange) {
        log.debug(ex.getMessage(), ex.getCause());
        return Mono.just(ex.getMessage());
    }

    @ExceptionHandler({DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<String> noDataFoundException(DataNotFoundException ex, ServerWebExchange exchange) {
        log.debug(ex.getMessage(), ex.getCause());
        return Mono.just(ex.getMessage());
    }


}
