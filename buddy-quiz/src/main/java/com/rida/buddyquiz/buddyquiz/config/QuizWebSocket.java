package com.rida.buddyquiz.buddyquiz.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rida.buddyquiz.buddyquiz.model.Quiz;
import com.rida.buddyquiz.buddyquiz.services.QuizService;

@Component
@ServerEndpoint("/ws/quiz")
public class QuizWebSocket {
    private static Set<Session> sessions = new HashSet<>();
    
    @Autowired
    private QuizService quizService;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(message);

            Long id = json.get("id").asLong();
            String answer = json.get("answer").asText();

            boolean result = quizService.checkAnswer(id, answer);

            Map<String, Object> response = new HashMap<>();
            response.put("result", result);

            session.getBasicRemote().sendText(mapper.writeValueAsString(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    public static void sendQuiz(Session session, Quiz quiz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> response = new HashMap<>();
            response.put("id", quiz.getId());
            response.put("question", quiz.getQuestion());
            response.put("choices", quiz.getChoices());

            session.getBasicRemote().sendText(mapper.writeValueAsString(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}