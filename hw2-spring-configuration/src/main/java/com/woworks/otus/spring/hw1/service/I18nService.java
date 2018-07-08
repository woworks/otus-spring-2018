package com.woworks.otus.spring.hw1.service;

import org.springframework.context.MessageSource;

import java.util.Locale;

public interface I18nService {
    String getMessage(String property);
}
