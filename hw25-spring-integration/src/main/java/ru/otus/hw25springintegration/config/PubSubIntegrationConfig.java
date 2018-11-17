package ru.otus.hw25springintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import ru.otus.hw25springintegration.domain.Person;
import ru.otus.hw25springintegration.integration.*;

@Configuration
@EnableIntegration
public class PubSubIntegrationConfig {

    @Bean
    public MessageChannel pubSubPersonChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    @BridgeFrom(value = "pubSubPersonChannel")
    public MessageChannel validationChannel() {
        return new ValidationMessageChannel();
    }

    @Bean
    @BridgeFrom(value = "pubSubPersonChannel")
    public MessageChannel persistenceChannel() {
        return new PersistenceMessageChannel();
    }

    @Bean
    @BridgeFrom(value = "pubSubPersonChannel")
    public MessageChannel auditChannel() {
        return new AuditMessageChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "pubSubPersonChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<Person> personMessageSource() {
        MessageSource sourceReader= new PersonMessageSource();
        return sourceReader;
    }

}


