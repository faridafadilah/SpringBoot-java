package com.farida.springboot.learnjpahibernate.course.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.farida.springboot.learnjpahibernate.course.Course;
import com.farida.springboot.learnjpahibernate.course.springdatajpa.CourseSpringDataJpaRepository;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

  // @Autowired
  // private CourseJpaRepository repository;

  @Autowired
  private CourseSpringDataJpaRepository repository;

  @Override
  public void run(String... args) throws Exception {
    // TODO Auto-generated method stub
    repository.save(new Course(1, "Learn AWS JPA", "farida"));
    repository.save(new Course(2, "Learn JavascriptJPA", "hisyam"));
    repository.save(new Course(3, "Learn Python", "hani"));

    repository.deleteById(1l);
    System.out.println(repository.findById(2l));
    System.out.println(repository.count());
    System.out.println(repository.findAll());

    System.out.println(repository.findByAuthor(""));
    System.out.println(repository.findByName("Learn JavascriptJPA"));
  }
  
}
