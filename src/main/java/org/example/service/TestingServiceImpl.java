package org.example.service;

import org.example.config.AppProps;
import org.example.domain.Question;
import org.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testingSystem")
public class TestingServiceImpl implements TestingService {
    private final QuestionService questionService;
    private final ConsoleIOService consoleService;
    private final AppProps props;

    @Autowired
    public TestingServiceImpl(QuestionService questionService, AppProps props, ConsoleIOService consoleService) {
        this.questionService = questionService;
        this.consoleService = consoleService;
        this.props = props;
    }

    @Override
    public String test(Student student) {
        int scores = 0;
        consoleService.out("\nYou have to get at least " + props.getScoresToPass() + " scores to pass the test\n");
        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            List<String> answers = question.getAnswers();
            consoleService.out("\n" + question.showQuestion());
            consoleService.out("\nEnter your answer: ");
            String studentsAnswer = consoleService.read();

            String[] studentAnswers = studentsAnswer.split(", ");
            for (String ans : studentAnswers) {
                for (String correctAnswer : answers) {
                    if (ans.equalsIgnoreCase(correctAnswer)) {
                        ++scores;
                    }
                }
            }
        }
        if (scores >= props.getScoresToPass()) {
            consoleService.out("\nYou've passed this test\n");
        } else {
            consoleService.out("\nYou didn't pass the test\n");
        }

        return student.getFirstName() + " " + student.getLastName() + " - your result is " + scores + " scores\n";
    }
}
