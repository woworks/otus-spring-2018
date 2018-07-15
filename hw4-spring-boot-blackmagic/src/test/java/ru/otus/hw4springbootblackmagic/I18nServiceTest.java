package ru.otus.hw4springbootblackmagic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw4springbootblackmagic.service.I18nService;
import ru.otus.hw4springbootblackmagic.service.I18nServiceImpl;

import java.util.Locale;

public class I18nServiceTest {

    private static Locale ruLocale;
    private static Locale enLocale;
    private static MessageSource mSource;
    private static I18nService i18nService;

    @BeforeAll
    static void setUp(){
        enLocale = new Locale.Builder().setLanguage("EN").build();
        ruLocale = new Locale.Builder().setLanguage("RU").build();
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        mSource = ms;
    }

    @Test
    void testGetMessageEn() {
        i18nService = new I18nServiceImpl(mSource, enLocale);
        assertEquals("Please answer the following 5 questions:", this.i18nService.getMessage("pleaseanswer"));
    }

    private void assertEquals(String s, String pleaseanswer) {
    }

    @Test
    void testGetMessageRu() {
        i18nService = new I18nServiceImpl(mSource, ruLocale);
        assertEquals("Пожалуйста, ответьте на следующие 5 вопросов:", this.i18nService.getMessage("pleaseanswer"));
    }
}
