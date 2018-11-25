package ru.otus.hw31hystrix.indicators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("app", "Feels Bad")
                    .withDetail("Error Code", errorCode).build();
        }
        return Health.up().withDetail("app", "Feels Good").build();
    }

    public int check() {
        return new Random().nextInt(10);
    }
}