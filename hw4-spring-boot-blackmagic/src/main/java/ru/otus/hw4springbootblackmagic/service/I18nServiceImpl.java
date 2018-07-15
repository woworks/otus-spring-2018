package ru.otus.hw4springbootblackmagic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@PropertySource("classpath:application.yml")
public class I18nServiceImpl implements I18nService {

    private final MessageSource messageSource;
    private final Locale locale;

    @Autowired
    public I18nServiceImpl(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public String getMessage(String property) {
        return messageSource.getMessage(property, null, this.locale);
    }
}
