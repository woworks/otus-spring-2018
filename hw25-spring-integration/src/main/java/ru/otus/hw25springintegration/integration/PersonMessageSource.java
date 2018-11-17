package ru.otus.hw25springintegration.integration;

import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import ru.otus.hw25springintegration.domain.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Random;

public class PersonMessageSource implements MessageSource {

    private final String[] names = {"Oliver","Jake","Noah","James", "Jack","Connor","Liam","John","Harry","Callum","Mason","Robert", "Jacob","Michael"};

    @Override
    public Message receive() {

        return new Message() {
            @Override
            public Object getPayload() {
                return getManagers();
            }

            @Override
            public MessageHeaders getHeaders() {
                return new MessageHeaders(Collections.singletonMap("created", LocalDateTime.now()));
            }
        };
    }

    private Person getManagers() {
        Person p = new Person();
        p.setId(new Random().nextLong());
        p.setFirstName(names[new Random().nextInt(names.length - 1)]);
        p.setLastName(names[new Random().nextInt(names.length - 1)]);
        p.setPosition("manager");
        p.setSalary(BigDecimal.valueOf(new Random().nextInt(2000)));
        p.setSex(Person.Sex.Male);

        return p;
    }
}
