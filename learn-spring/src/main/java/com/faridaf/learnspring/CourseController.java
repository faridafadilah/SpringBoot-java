package com.faridaf.learnspring;

import java.util.List;
import java.util.Arrays;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //anotasi untuk menandakan class ini controller
public class CourseController {
  // route /course
  // create simple rest api
  // Course: id, name, author

  @RequestMapping("/course") // untuk membuat route
  public List<Course> retrieveAllCourse() {
    return Arrays.asList(
      new Course(1, "Learn AWS", "inFarda"),
      new Course(2, "Learn DevOps", "inFarida"),
      new Course(3, "Learn Javascript", "inRida")
    );
  }
}
