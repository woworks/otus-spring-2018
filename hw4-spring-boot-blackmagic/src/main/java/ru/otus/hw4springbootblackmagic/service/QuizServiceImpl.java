package ru.otus.hw4springbootblackmagic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.hw4springbootblackmagic.domain.Question;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionService questionService;
    private final I18nService i18nService;

    @Autowired
    public QuizServiceImpl(QuestionService service, I18nService i18nService) {
        this.questionService = service;
        this.i18nService = i18nService;
    }

    @Override
    public void runQuiz() {
        int score = 0;

        List<String> answers = Arrays.asList("1", "2", "3", "4");
        System.out.println(this.i18nService.getMessage("pleaseanswer"));

        Scanner scanner = new Scanner(System.in);

        for (Question question : this.questionService.getAll()) {
            System.out.println(question.getQuestion() + "(1,2,3,4)");
            for (int i = 0; i < question.getAnswers().size(); i++) {
                System.out.println((i + 1) + ": " + question.getAnswers().get(i));
            }

            String answer = scanner.nextLine();
            while (!answers.contains(answer)) {
                System.out.println(this.i18nService.getMessage("wronginput"));
                answer = scanner.nextLine();
            }

            String userAnswer = question.getAnswers().get(Integer.valueOf(answer) - 1);
            System.out.println(this.i18nService.getMessage("youranswer") + userAnswer);

            if (question.getCorrectAnswer().equals(userAnswer)) {
                score++;
            }
        }

        System.out.println(this.i18nService.getMessage("yourscore") + score);
    }
}
