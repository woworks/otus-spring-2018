package ru.otus.hw25springintegration;

import org.junit.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import ru.otus.hw25springintegration.domain.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class MessagesTest {

    @Test
    public void testCreatePersonMessage() {
        Person person = getPerson();
        Message<Person> message = new GenericMessage<Person>(person);

        assertNotNull(message);
        assertNotNull(message.getPayload());
        assertEquals(person, message.getPayload());
    }

    @Test
    public void testGenericMessageWithHeaders() {
        Person person = getPerson();
        LocalDateTime created = LocalDateTime.now();
        Map<String, Object> headers = Collections.singletonMap("created", created);
        Message<Person> message = new GenericMessage<>(person, headers);

        assertNotNull(message);
        assertEquals(person, message.getPayload());
        assertEquals(created, message.getHeaders().get("created", LocalDateTime.class));
    }

    private Person getPerson(){
        return new Person(1, "Jim", "Jameson", "worker", Person.Sex.Male, new BigDecimal(1000L));
    }
}
