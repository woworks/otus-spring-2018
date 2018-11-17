package ru.otus.hw25springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;

@SpringBootApplication
public class Hw25SpringIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw25SpringIntegrationApplication.class, args);
    }

    @Bean
    public IntegrationFlow personFlow() {
        return f -> f
                .channel("p2pChannel")
                .split()
                .log()
                .aggregate();
    }
}
