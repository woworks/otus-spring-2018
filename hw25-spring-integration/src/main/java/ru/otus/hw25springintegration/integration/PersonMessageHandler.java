package ru.otus.hw25springintegration.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import ru.otus.hw25springintegration.domain.Person;


public class PersonMessageHandler implements MessageHandler {
    Logger LOG = LoggerFactory.getLogger(PersonMessageHandler.class);

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        LOG.info("P2P:: Person was created at: {};  {}", message.getHeaders().get("created"), (Person) message.getPayload());
    }
}