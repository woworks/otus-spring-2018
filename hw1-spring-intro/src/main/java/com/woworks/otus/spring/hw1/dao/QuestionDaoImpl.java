package com.woworks.otus.spring.hw1.dao;

import com.woworks.otus.spring.hw1.domain.Question;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class QuestionDaoImpl implements QuestionDao {
    public List<Question> findAll() {

        List<Question> questions = new ArrayList<>();
        Path path;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource("quiz.csv").toURI());

            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> questions.add(lineToQuestion(line)));
            lines.close();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private static Question lineToQuestion(String line) {
        Question question = new Question();
        String[] lineArray = line.split(",");
        question.setQuestion(lineArray[0]);
        List<String> answers = new ArrayList<>();
        answers.add(lineArray[1]);
        answers.add(lineArray[2]);
        answers.add(lineArray[3]);
        answers.add(lineArray[4]);
        question.setAnswers(answers);
        question.setCorrectAnswer(lineArray[5]);
        return question;
    }
}
