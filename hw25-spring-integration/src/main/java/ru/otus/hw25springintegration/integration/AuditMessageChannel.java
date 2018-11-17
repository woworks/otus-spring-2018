package ru.otus.hw25springintegration.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import ru.otus.hw25springintegration.domain.Person;

import java.math.BigDecimal;

public class AuditMessageChannel implements MessageChannel {
    Logger LOG = LoggerFactory.getLogger(AuditMessageChannel.class);

    @Override
    public boolean send(Message<?> message) {
        return audit(message);
    }

    @Override
    public boolean send(Message<?> message, long l) {
        return audit(message);
    }

    private boolean audit(Message<?> message) {
        if (message.getPayload() instanceof Person
                && message.getPayload() != null) {
            Person person = ((Person) message.getPayload());
            if (person.getSalary().longValue() >= 1800L) {
                LOG.warn("Audit:: Person {} has salary {}, which is too much!", person.getId(), person.getSalary());
            } else {
                LOG.info("Audited person with id: {}; salary {} is OK", person.getId(), person.getSalary());
            }
        }

        return true;
    }
}
