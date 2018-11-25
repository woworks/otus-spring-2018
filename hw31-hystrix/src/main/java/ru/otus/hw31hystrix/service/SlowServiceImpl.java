package ru.otus.hw31hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SlowServiceImpl implements SlowService {

    @Override
    public String slowMethod() {
       if (new Random().nextBoolean()){
           throw new RuntimeException("Slow Method Fails!");
       }

        return "slowMethod for now works fast!";
    }

}
