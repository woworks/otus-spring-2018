package ru.otus.hw4springbootblackmagic.dao;


import ru.otus.hw4springbootblackmagic.domain.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findAll();
}
