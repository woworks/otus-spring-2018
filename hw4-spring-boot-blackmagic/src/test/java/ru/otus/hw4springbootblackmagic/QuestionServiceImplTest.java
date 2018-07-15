package ru.otus.hw4springbootblackmagic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.otus.hw4springbootblackmagic.dao.QuestionDao;
import ru.otus.hw4springbootblackmagic.mock.QuestionDaoMockImpl;
import ru.otus.hw4springbootblackmagic.service.QuestionService;
import ru.otus.hw4springbootblackmagic.service.QuestionServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

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
