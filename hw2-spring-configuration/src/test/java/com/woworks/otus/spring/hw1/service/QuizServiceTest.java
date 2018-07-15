package com.woworks.otus.spring.hw1.service;

import com.woworks.otus.spring.hw1.service.mock.I18nServiceMockImpl;
import com.woworks.otus.spring.hw1.service.mock.QuestionServiceMockImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class QuizServiceTest {

    private static QuestionService questionService;
    private static I18nService i18nService;
    private static QuizService quizService;

    @BeforeAll
    static void setUp() {
        questionService = new QuestionServiceMockImpl();
        i18nService = new I18nServiceMockImpl();
        quizService = new QuizServiceImpl(questionService, i18nService);

    }

    @Test
    @Disabled("Cannot test user input")
    public void testRunQuiz() {
        quizService.runQuiz();
    }
}
