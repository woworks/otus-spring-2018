package ru.otus.hw25springintegration.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import ru.otus.hw25springintegration.domain.Person;

public class PersistenceMessageChannel implements MessageChannel {
    Logger LOG = LoggerFactory.getLogger(PersistenceMessageChannel.class);
    @Override
    public boolean send(Message<?> message) {
        return persist(message);
    }

    @Override
    public boolean send(Message<?> message, long l) {
        return persist(message);
    }

    private boolean persist(Message<?> message){
        if (message.getPayload() instanceof Person
                && message.getPayload()  != null) {
            LOG.info("Persisted person with id: {}", ((Person) message.getPayload()).getId());
        }

        return true;
    }
}
