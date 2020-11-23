package com.example.demo;

import org.example.config.AppProps;
import org.example.domain.Question;
import org.example.domain.Student;
import org.example.service.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@EnableConfigurationProperties
@SpringBootTest
class SpringBootTestingSystemApplicationTests {

    @MockBean
    @Qualifier("consoleIOService")
    private IOService consoleService;

    @Autowired
    private LocalizationService localizationService;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private StudentService studentService;

    @Autowired
    private AppProps props;

    @Autowired
    private TestingService testingService;

    private List<Question> questions;

    private Student student;


    @ComponentScan(value = "org.example.service")
    @ConfigurationProperties(prefix = "application")
    @Configuration
    static class AppConfigTest {
        @Bean
        public AppProps getProps() {
            return new AppProps();
        }


        @Bean
        public ConsoleIOService getConsole() {
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream("365".getBytes());
            return new ConsoleIOService(arrayInputStream, System.out);
        }
    }

    @BeforeTestExecution
    public void setUp() {

    }

    @Test
    void TestingServiceTest() {
        questions = new ArrayList<>();
        questions.add(new Question("How many days are in a year?", List.of("365")));
        student = new Student("Vasya", "Ivanov");
        when(questionService.getQuestions()).thenReturn(questions);
        assertThat(testingService.test(student)).isEqualTo(student.getFirstName() + " " + student.getLastName() + " - your result is " + 1 + " scores\n");
    }

}
