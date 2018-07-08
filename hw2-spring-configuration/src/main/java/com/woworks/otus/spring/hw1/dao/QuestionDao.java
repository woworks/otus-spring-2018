package com.woworks.otus.spring.hw1.dao;

import com.woworks.otus.spring.hw1.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
