package ru.otus.hw4springbootblackmagic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.otus.hw4springbootblackmagic.mock.I18nServiceMockImpl;
import ru.otus.hw4springbootblackmagic.service.I18nService;
import ru.otus.hw4springbootblackmagic.service.QuestionService;
import ru.otus.hw4springbootblackmagic.service.QuizService;
import ru.otus.hw4springbootblackmagic.service.QuizServiceImpl;

public class QuizServiceTest {

    private static QuestionService questionService;
    private static I18nService i18nService;
    private static QuizService quizService;

    @BeforeAll
    static void setUp() {
        i18nService = new I18nServiceMockImpl();
        quizService = new QuizServiceImpl(questionService, i18nService);

    }

    @Test
    @Disabled("Cannot test user input")
    public void testRunQuiz() {
        quizService.runQuiz();
    }
}
