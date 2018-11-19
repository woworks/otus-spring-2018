package ru.otus.hw25springintegration.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.hw25springintegration.domain.Person;

import java.util.Collection;

//@MessagingGateway
public interface PersonMessageService {

   //@SuppressWarnings("UnresolvedMessageChannel")
   // @Gateway(requestChannel = "personFlow.input")
    Collection<Person> upperStrings(Collection<Person> str);
}
