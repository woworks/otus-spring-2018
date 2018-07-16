package ru.otus.hw4springbootblackmagic.mock;


import ru.otus.hw4springbootblackmagic.domain.Question;
import ru.otus.hw4springbootblackmagic.service.QuestionService;

import java.util.List;

public class QuestionServiceMockImpl implements QuestionService {
    @Override
    public List<Question> getAll() {
    return new QuestionDaoMockImpl().findAll();
    }
}
