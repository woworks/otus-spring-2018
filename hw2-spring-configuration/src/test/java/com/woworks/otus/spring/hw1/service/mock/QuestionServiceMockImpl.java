package com.woworks.otus.spring.hw1.service.mock;

import com.woworks.otus.spring.hw1.domain.Question;
import com.woworks.otus.spring.hw1.service.QuestionService;

import java.util.List;

public class QuestionServiceMockImpl implements QuestionService {
    @Override
    public List<Question> getAll() {
    return new QuestionDaoMockImpl().findAll();
    }
}
