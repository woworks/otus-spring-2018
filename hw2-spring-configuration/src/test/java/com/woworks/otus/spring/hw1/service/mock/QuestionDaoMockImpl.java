package com.woworks.otus.spring.hw1.service.mock;

import com.woworks.otus.spring.hw1.dao.QuestionDao;
import com.woworks.otus.spring.hw1.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionDaoMockImpl implements QuestionDao {
    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Question question = new Question();
            question.setQuestion("Question" + 1);

            List<String> answers = new ArrayList<>();
            for(int j = 1; j < 5; j++) {
                answers.add("Answer" + j);
            }
            question.setAnswers(answers);
            question.setCorrectAnswer("Answer" + (new Random().nextInt(3) + 1));
            questions.add(question);
        }
        return questions;
    }
}
