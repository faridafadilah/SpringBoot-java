package com.example.redisreactive.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.example.redisreactive.model.Book;
import com.example.redisreactive.repository.RedisBookRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
  private final RedisBookRepository bookRepository;

  @Override
  public Mono<Book> create(@Valid Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Flux<Book> getAll() {
    return bookRepository.getAll();
  }

  @Override
  public Mono<Long> deleteById(String id) {
    return bookRepository.delete(id);
  }

  @Override
  public Mono<Book> getOne(String id) {
    return bookRepository.get(id);
  }
  
}
