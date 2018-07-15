package com.woworks.otus.spring.hw1.service;

import com.woworks.otus.spring.hw1.dao.QuestionDao;
import com.woworks.otus.spring.hw1.service.mock.QuestionDaoMockImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionServiceImplTest {

    private static QuestionService questionService;
    private static QuestionDao dao = new QuestionDaoMockImpl();

    @BeforeAll
    static void setUp() {
        questionService = new QuestionServiceImpl(dao);
    }

    @Test
    void testGetAll() {
        assertNotNull(questionService.getAll());
        assertEquals(5, questionService.getAll().size());

        questionService.getAll().forEach(question -> {
            assertTrue(question.getAnswers().contains(question.getCorrectAnswer()));
        });
    }
}
