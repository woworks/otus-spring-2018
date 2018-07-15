package ru.otus.hw4springbootblackmagic.service;


import org.springframework.stereotype.Service;
import ru.otus.hw4springbootblackmagic.dao.QuestionDao;
import ru.otus.hw4springbootblackmagic.domain.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public List<Question> getAll() {
        return dao.findAll();
    }
}
