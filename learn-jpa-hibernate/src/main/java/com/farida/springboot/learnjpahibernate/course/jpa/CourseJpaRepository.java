package com.farida.springboot.learnjpahibernate.course.jpa;

import org.springframework.stereotype.Repository;

import com.farida.springboot.learnjpahibernate.course.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional // untuk menjalankan query pada jpa
public class CourseJpaRepository {
  @PersistenceContext
  private EntityManager entityManager;

  //Menambah data
  public void insert(Course course) {
    entityManager.merge(course);
  }
  //Mencari data berdasarkan id
  public Course findById(long id) {
    return entityManager.find(Course.class, id);
  }
  //Delete Data berdasarkan id
  public void deleteById(long id) {
    Course course = entityManager.find(Course.class, id);
    entityManager.remove(course);
  }
}
