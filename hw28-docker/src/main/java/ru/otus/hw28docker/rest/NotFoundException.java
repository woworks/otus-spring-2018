package ru.otus.hw28docker.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException{

    NotFoundException() {
    }
}
