package ru.otus.hw4springbootblackmagic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw4springbootblackmagic.service.QuizService;

import java.util.Locale;

@SpringBootApplication
public class Hw4SpringBootBlackmagicApplication {

	public static void main(String[] args) {
	    ApplicationContext context = SpringApplication.run(Hw4SpringBootBlackmagicApplication.class);
        QuizService quizService = context.getBean(QuizService.class);
        quizService.runQuiz();
	}

	@Bean
	public Locale getLocale(@Value("${quiz.language}") String language) {
		return new Locale.Builder().setLanguage(language).build();
	}

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}
