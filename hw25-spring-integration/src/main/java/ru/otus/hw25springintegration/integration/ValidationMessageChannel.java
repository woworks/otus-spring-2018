package ru.otus.hw25springintegration.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import ru.otus.hw25springintegration.domain.Person;

public class ValidationMessageChannel implements MessageChannel {
    Logger LOG = LoggerFactory.getLogger(ValidationMessageChannel.class);
    @Override
    public boolean send(Message<?> message) {
        return hasValidId(message);
    }

    @Override
    public boolean send(Message<?> message, long l) {
        return hasValidId(message);
    }

    private boolean hasValidId(Message<?> message){
        if ((message.getPayload() instanceof Person)
                && message.getPayload()  != null
                && ((Person)(message.getPayload())).getId() < 0) {
            Person person = ((Person) message.getPayload());
            LOG.error("Person {} has incorrect ID", person.getId());

        } else {
            Person person = ((Person) message.getPayload());
            LOG.debug("Validated person with Id: {}; OK", person.getId());
        }

        return true;
    }
}
