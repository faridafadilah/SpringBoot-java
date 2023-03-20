package com.example.redisreactive.repository;

import com.example.redisreactive.model.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepository {
  Mono<Book> save(Book book);
  Mono<Book> get(String key);
  Flux<Book> getAll();
  Mono<Long> delete(String id);
}
