package ru.otus.hw4springbootblackmagic.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw4springbootblackmagic.dao.QuestionDao;
import ru.otus.hw4springbootblackmagic.service.QuizService;

import java.util.Locale;


@ShellComponent
public class QuizCommands {
    private final QuizService quizService;
    private final QuestionDao questionDao;

    @Autowired
    public QuizCommands(QuizService quizService, QuestionDao questionDao) {
        this.quizService = quizService;
        this.questionDao = questionDao;
    }

    @ShellMethod("Runs the quiz")
    public void quiz() {
        this.quizService.runQuiz();
    }

    @ShellMethod("Display quiz language")
    public String lang() {
        Locale locale = LocaleContextHolder.getLocale();
        return locale.getDisplayLanguage();
    }

    @ShellMethod("Display file name where quiz questions are stored")
    public String file() {
        return this.questionDao.getFileName();
    }
}
