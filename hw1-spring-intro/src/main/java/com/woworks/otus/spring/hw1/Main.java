package com.woworks.otus.spring.hw1;

import com.woworks.otus.spring.hw1.domain.Question;
import com.woworks.otus.spring.hw1.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        runQuiz();
    }


    private static void runQuiz() {

        int score = 0;

        List<String> answers = Arrays.asList("1", "2", "3", "4");
        System.out.println("Please answer the following 5 questions:");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        List<Question> questions = service.getAll();
        System.out.println("questions: " + questions.toString());

        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestion() + "(1,2,3,4)");
            for (int i = 0; i < question.getAnswers().size(); i++) {
                System.out.println((i + 1) + ": " + question.getAnswers().get(i));
            }

            String answer = scanner.nextLine();
            while (!answers.contains(answer)) {
                System.out.println("Wrong input!. Correct input - 1 or 2 or 3 or 4");
                answer = scanner.nextLine();
            }

            String userAnswer = question.getAnswers().get(Integer.valueOf(answer) - 1);
            System.out.println("Your answer: " + userAnswer);

            if (question.getCorrectAnswer().equals(userAnswer)) {
                score++;
            }
        }

        System.out.println("Your score is: " + score);
    }
}
