package org.example.service;

import org.example.config.AppProps;
import org.example.domain.Question;
import org.example.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("testingSystem")
public class TestingServiceImpl implements TestingService {
    private final QuestionService questionService;
    private final IOService consoleIOService;
    private final MessageSourceLocalizationService localizationService;
    private final AppProps props;

    @Autowired
    public TestingServiceImpl(QuestionService questionService, AppProps props,
                              MessageSourceLocalizationService localizationService,
                              ConsoleIOService consoleIOService) {
        this.questionService = questionService;
        this.localizationService = localizationService;
        this.consoleIOService = consoleIOService;
        this.props = props;
    }

    @Override
    public String test(Student student) {
        int scores = 0;
        consoleIOService.out(localizationService.getMessage("test.infoToPass", props.getScoresToPass()) + "\n");
        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            List<String> answers = question.getAnswers();
            consoleIOService.out("\n" + question.showQuestion() + "\n");
            consoleIOService.out(localizationService.getMessage("test.answer"));
            String studentsAnswer = consoleIOService.read();

            var studentAnswers = Arrays.stream(studentsAnswer.split(", ")).collect(Collectors.toSet());
            for (String ans : studentAnswers) {
                if (answers.contains(ans.trim().toLowerCase())) {
                    ++scores;
                }
            }
        }
        if (scores >= props.getScoresToPass()) {
            consoleIOService.out("\n" + localizationService.getMessage("test.passed") + "\n");
        } else {
            consoleIOService.out("\n" + localizationService.getMessage("test.failed") + "\n");
        }
        return localizationService.getMessage("test.result",
                student.getFirstName(), student.getLastName(), scores) + "\n";
    }
}
