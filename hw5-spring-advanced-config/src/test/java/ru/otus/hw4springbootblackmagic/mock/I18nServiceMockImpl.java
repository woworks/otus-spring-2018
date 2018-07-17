package ru.otus.hw4springbootblackmagic.mock;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw4springbootblackmagic.service.I18nService;

import java.util.Locale;

public class I18nServiceMockImpl implements I18nService {

    private final MessageSource messageSource;
    private final Locale locale;

    public I18nServiceMockImpl() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        this.messageSource = ms;
        this.locale = new Locale.Builder().setLanguage("EN").build();
    }

    @Override
    public String getMessage(String property) {
        return messageSource.getMessage(property, null, this.locale);
    }
}
