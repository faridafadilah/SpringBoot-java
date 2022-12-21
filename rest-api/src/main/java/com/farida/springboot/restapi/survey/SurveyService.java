package com.farida.springboot.restapi.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();

	static {

		Question question1 = new Question("Question1",
				"Most Popular Cloud Platform Today", Arrays.asList(
						"AWS", "Azure", "Google Cloud", "Oracle Cloud"),
				"AWS");
		Question question2 = new Question("Question2",
				"Fastest Growing Cloud Platform", Arrays.asList(
						"AWS", "Azure", "Google Cloud", "Oracle Cloud"),
				"Google Cloud");
		Question question3 = new Question("Question3",
				"Most Popular DevOps Tool", Arrays.asList(
						"Kubernetes", "Docker", "Terraform", "Azure DevOps"),
				"Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
				question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey",
				"Description of the Survey", questions);

		surveys.add(survey);

	}
	//Menampilkan data
	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}
	//Method untuk Menampilkan data berdasarkan id
	public Survey retrieveSurveyById(String surveyId) {
		Predicate<? super Survey> predicate = survey -> survey.getId().equalsIgnoreCase(surveyId);
		Optional<Survey> optionalSurvey = surveys.stream().filter(predicate).findFirst();
		//jika kosong == null
		if (optionalSurvey.isEmpty())
			return null;
		return optionalSurvey.get();
	}
	//Method menampilkan semua question
	public List<Question> retrieveAllSurveyQuestion(String surveyId) {
		Survey survey = retrieveSurveyById(surveyId);
		if (survey == null) {
			return null;
		}
		return survey.getQuestion();
	}
	//Method menampilkan sepesifik question
	public Question retrieveSpesificSurveyQuestion(String surveyId, String questionId) {
		List<Question> surveyQuestions = retrieveAllSurveyQuestion(surveyId);
		if (surveyQuestions == null) {
			return null;
		}
		Optional<Question> optionalQuestion = surveyQuestions.stream().filter(q -> q.getId().equalsIgnoreCase(questionId))
				.findFirst();
		if (optionalQuestion.isEmpty())
			return null;
		return optionalQuestion.get();
	}
	//Menambah data
	public String addNewSurveyQuestion(String surveyId, Question question) {
		List<Question> questions = retrieveAllSurveyQuestion(surveyId);
		question.setId(generateRandomId());
		questions.add(question);
		return question.getId();
	}
	//Id random untuk tambah data
	private String generateRandomId() {
		SecureRandom secureRandom = new SecureRandom();
		String randomId = new BigInteger(32, secureRandom).toString();
		return randomId;
	}
	//Method untuk menghapus question
	public String deleteSurveyQuestion(String surveyId, String questionId) {
		List<Question> surveyQuestions = retrieveAllSurveyQuestion(surveyId);
		if (surveyQuestions == null) return null;
		Predicate<? super Question> predicate = q -> q.getId().equalsIgnoreCase(questionId);
		boolean removed = surveyQuestions.removeIf(predicate);
		if(!removed) return null;
		return questionId;
	}
	public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
		List<Question> questions = retrieveAllSurveyQuestion(surveyId);
		questions.removeIf(q -> q.getId().equalsIgnoreCase(questionId));
		questions.add(question);
	}
}