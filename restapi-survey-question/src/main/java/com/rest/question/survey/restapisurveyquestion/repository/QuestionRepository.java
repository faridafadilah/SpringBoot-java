package com.rest.question.survey.restapisurveyquestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rest.question.survey.restapisurveyquestion.dto.QuestionDto;
import com.rest.question.survey.restapisurveyquestion.models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    // SELECT s.id, s.title, q.question_id, q.correct_answer  FROM question q INNER JOIN survey s ON s.id = q.survey_id
	@Query(
    value = "SELECT s.id as idSurvey, s.title as titleSurvey, q.id as idQuestion, q.correct_answer as correctAnswer " +
    "FROM question q INNER JOIN survey s ON s.id = q.survey_id ",
    nativeQuery = true
  )
  List<QuestionDto> getAllQuestion();

  // Page<Question> findByCorrectAnswerContaining(String correctAnswer, Pageable pageable);
}
