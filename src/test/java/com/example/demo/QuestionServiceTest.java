package com.example.demo;

import org.example.dao.QuestionDao;
import org.example.domain.Question;
import org.example.service.QuestionService;
import org.example.service.QuestionServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Testing questionService")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class QuestionServiceTest {

    @Mock
    QuestionDao dao;

    @InjectMocks
    QuestionServiceImpl questionService;

    @DisplayName("Getting a question from question service")
    @Test
    public void getQuestionFromQuestionService() {
        when(dao.readQuestions()).thenReturn(List.of(new Question("How many apples would you eat?", List.of("5"))));
        assertEquals("How many apples would you eat?", questionService.getQuestions().get(0).showQuestion());
        assertEquals("5", questionService.getQuestions().get(0).getAnswers().get(0));
    }

    @DisplayName("Getting a question from question service when dao returns null")
    @Test
    public void getQuestionFromQuestionServiceWhenDaoReturnsNull() {
        when(dao.readQuestions()).thenReturn(null);
        assertNull(questionService.getQuestions());
    }

}
