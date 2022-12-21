// package com.farida.springboot.learnjpahibernate.course.jdbc;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.stereotype.Repository;

// import com.farida.springboot.learnjpahibernate.course.Course;

// @Repository // anotasi untuk beraksi dengan database
// public class CourseJdbcRepository {
//   @Autowired
//   private JdbcTemplate springJdbcTemplate;
//   private static String INSERT_QUERY = 
//     """
//         insert into course (id, name, author)
//         values(?, ?, ?);
//         """;

//   private static String DELETE_QUERY = 
//     """
//         delete from course where id = ?
//         """;

//   private static String SELECT_QUERY =
//     """
//         select * from course where id = ?
//         """;

//   public void insert(Course course) {
//     springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
//   }

//   public void delete(long id) {
//     springJdbcTemplate.update(DELETE_QUERY, id);
//   }

//   public Course findById(long id) {
//     //REsult Set -> Bean -> Row Mapper => 
//     return springJdbcTemplate.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
//   }
// }
