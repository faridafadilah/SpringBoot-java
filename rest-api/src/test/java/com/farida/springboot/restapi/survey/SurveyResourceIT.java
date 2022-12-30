package com.farida.springboot.restapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIT {
  private static String SPECIFIC_QUESTION_URL = "/surveys/Survey1/questions/Question1";
  private static String GENERIC_QUESTIONS_URL = "/surveys/Survey1/questions";

  @Autowired
  private TestRestTemplate template;

  // Test Url dan Response
	String str = """
      {
        "id": "Question1",
        "description": "Most Popular Cloud Platform Today",
        "options": [
          "AWS",
          "Azure",
          "Google Cloud",
          "Oracle Cloud"
        ],
        "correctAnswer": "AWS"
      }
  """;

  @Test
	void retrieveSpecificSurveyQuestion() throws JSONException{
    ResponseEntity<String> responseEntity	= template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
		String expectedResult = """
      {
        "id":"Question1",
        "description":"Most Popular Cloud Platform Today",
        "correctAnswer":"AWS"
      }
    """;

    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    JSONAssert.assertEquals(expectedResult, responseEntity.getBody(), false);
  }

  @Test
	void retrieveAllSurveyQuestion() throws JSONException{
    ResponseEntity<String> responseEntity	= template.getForEntity(GENERIC_QUESTIONS_URL, String.class);
		// Baris yang ringkas dan penting saja
    String expectedResult = """
      [
        {
          "id": "Question1"
        },
        {
          "id": "Question2"
        },
        {
          "id": "Question3"
        }
      ]
    """;

    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    JSONAssert.assertEquals(expectedResult, responseEntity.getBody(), false);
  }

  @Test
  void addNewSurveyQuestion_basicScenario() {
    String requestBody = """
      {
        "description": "Fastest Growing Cloud Platform updated",
        "options": [
            "AWS",
            "Azure",
            "Google Cloud",
            "Oracle Cloud"
        ],
        "correctAnswer": "Google Cloud updated"
      }
    """;

    //POST
    //Request Body
    //http://localhost:8080/surveys/Survey1/questions

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");

    HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
    
    //Exchange mengurus tentang http dan lainnya
    ResponseEntity<String> responseEntity = template.exchange(GENERIC_QUESTIONS_URL, HttpMethod.POST, httpEntity, String.class);

    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

    String locationHeader = responseEntity.getHeaders().get("Location").get(0);
    assertTrue(locationHeader.contains("/surveys/Survey1/questions"));

    //DELETE
    //locationHeader
    //REMOVE SIDE EFFECT
    template.delete(locationHeader);
  }

}
