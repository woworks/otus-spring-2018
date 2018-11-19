package ru.otus.hw25springintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import ru.otus.hw25springintegration.domain.Person;
import ru.otus.hw25springintegration.integration.PersonMessageHandler;
import ru.otus.hw25springintegration.integration.PersonMessageSource;

@Configuration
@EnableIntegration
public class P2pIntegrationConfig {

    @Bean
    public MessageChannel p2pChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "p2pChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<Person> personReadingMessageSource() {
        MessageSource sourceReader= new PersonMessageSource();
        return sourceReader;
    }

    @Bean
    @ServiceActivator(inputChannel= "p2pChannel")
    public MessageHandler personMessageHandler() {
        MessageHandler handler = new PersonMessageHandler();
        return handler;
    }
}


