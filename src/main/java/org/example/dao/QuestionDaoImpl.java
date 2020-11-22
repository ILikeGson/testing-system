package org.example.dao;

import org.example.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class QuestionDaoImpl implements QuestionDao {
    private final String URL;

    public QuestionDaoImpl(String url) {
        this.URL = url;
    }

    public List<Question> readQuestions() {
        List<Question> questions = new ArrayList<>();
        InputStream inputStream = null;
        Scanner scanner = null;
        try {
            inputStream = getClass().getResourceAsStream(URL);
            scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                questions.add(parseString(scanner.nextLine()));
            }
        } finally {
            try {
                inputStream.close();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }

    private Question parseString(String unparsedQuestion) {
        if (Objects.nonNull(unparsedQuestion)) {
            String[] elements = unparsedQuestion.split(";");
            return new Question(elements[1], Arrays.stream(elements).skip(2).collect(Collectors.toList()));
        }
        return null;
    }
}
