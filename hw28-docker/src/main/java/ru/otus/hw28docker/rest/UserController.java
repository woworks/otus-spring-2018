package ru.otus.hw28docker.rest;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw28docker.domain.User;

@RestController
@CrossOrigin
public class UserController {

    private final MeterRegistry meterRegistry;

    UserController(MeterRegistry meterRegistry){
        this.meterRegistry = meterRegistry;
    }

    @RequestMapping("/authenticate")
    public User authenticate(@RequestBody User user) {
        if (user.getUsername().equals("user") && user.getPassword().equals("password")) {
            meterRegistry.counter("counter.login.success").increment();
            return user;
        }

        meterRegistry.counter("counter.login.failure").increment();
        return null;
    }


}
