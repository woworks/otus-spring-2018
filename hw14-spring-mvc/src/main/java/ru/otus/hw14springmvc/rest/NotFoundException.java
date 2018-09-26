package ru.otus.hw14springmvc.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException{

    NotFoundException() {
    }
}
