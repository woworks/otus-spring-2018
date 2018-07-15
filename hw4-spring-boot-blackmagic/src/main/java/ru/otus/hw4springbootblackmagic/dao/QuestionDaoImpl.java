package ru.otus.hw4springbootblackmagic.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.hw4springbootblackmagic.domain.Question;
import ru.otus.hw4springbootblackmagic.service.I18nService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
@PropertySource("classpath:application.yml")
public class QuestionDaoImpl implements QuestionDao {

    private final String fileName;
    private final I18nService i18nService;

    @Autowired
    public QuestionDaoImpl(@Value("${quiz.filename}") String fileName, I18nService i18nService) {
        this.fileName = fileName;
        this.i18nService = i18nService;
    }

    public List<Question> findAll() {

        List<Question> questions = new ArrayList<>();
        Path path;
        try {
            path = Paths.get(getClass().getClassLoader()
                    .getResource(this.fileName).toURI());

            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> questions.add(lineToQuestion(line)));
            lines.close();

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return questions;
    }

    private Question lineToQuestion(String line) {
        Question question = new Question();
        String[] lineArray = line.split(",");
        question.setQuestion(this.i18nService.getMessage(lineArray[0]));

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
