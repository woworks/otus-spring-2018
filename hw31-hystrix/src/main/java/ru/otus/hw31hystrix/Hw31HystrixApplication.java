package ru.otus.hw31hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class Hw31HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw31HystrixApplication.class, args);
    }
}
