package com.woworks.otus.spring.hw1.service;

import com.woworks.otus.spring.hw1.dao.QuestionDao;
import com.woworks.otus.spring.hw1.domain.Question;
import org.springframework.stereotype.Service;

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
