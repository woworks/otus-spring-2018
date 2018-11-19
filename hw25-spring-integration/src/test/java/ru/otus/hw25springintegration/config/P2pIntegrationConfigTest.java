package ru.otus.hw25springintegration.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.endpoint.IntegrationConsumer;
import org.springframework.integration.endpoint.PollingConsumer;
import org.springframework.integration.test.context.MockIntegrationContext;
import org.springframework.integration.test.context.SpringIntegrationTest;
import org.springframework.integration.test.mock.MockIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.hw25springintegration.domain.Person;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringIntegrationTest(noAutoStartup = {"p2pChannel", "*Source*"})
@SpringBootTest
public class P2pIntegrationConfigTest {

    @InboundChannelAdapter(channel = "p2pChannel")
    @Bean
    public MessageSource<Person> testingMessageSource() {
        return MockIntegration.mockMessageSource(getPerson());
    }


    @InboundChannelAdapter(channel = "p2pChannel")
    @Bean
    public MessageHandler testingMessageHandler() {
        return MockIntegration.mockMessageHandler();
    }

    @Autowired
    private MockIntegrationContext mockIntegrationContext;

    @Autowired
    private MessageSource personReadingMessageSource;

/*    @Autowired
    private MessageHandler personReadingMessagehandler;*/

    @Test
    public void p2pChannel() {
    }


    @Test
    public void testPersonReadingMessageSource() {
        Message<?> receive = this.personReadingMessageSource.receive();
        assertNotNull(receive);
    }

/*    @Test
    public void personMessageHandler() {
        Message<Person> message = new GenericMessage<Person>(getPerson());
        MessageHandler messageHandler = (m) -> {
            System.out.println("m = " + m);
        };

        IntegrationConsumer messageConsumer = new PollingConsumer(new PollableChannel() {
            @Override
            public Message<?> receive() {
                return null;
            }

            @Override
            public Message<?> receive(long timeout) {
                return null;
            }

            @Override
            public boolean send(Message<?> message, long timeout) {
                return false;
            }
        }, messageHandler);
        mockIntegrationContext.substituteMessageHandlerFor("personMessageHandler", messageHandler);

    }*/

    private static Person getPerson(){
        return new Person(1, "Jim", "Jameson", "worker", Person.Sex.Male, new BigDecimal(1000L));
    }


}
