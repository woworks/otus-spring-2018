package ru.otus.hw31hystrix.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw31hystrix.domain.Genre;
import ru.otus.hw31hystrix.service.GenreService;
import ru.otus.hw31hystrix.service.SlowService;

import java.util.List;

@RestController
@CrossOrigin
public class SlowController {

    private final SlowService slowService;
    @Autowired
    public SlowController(SlowService slowService) {
        this.slowService = slowService;
    }

    @HystrixCommand(fallbackMethod = "dummyHelper", groupKey = "SlowService", commandKey = "slowMethod")
    @GetMapping("api/slow")
    @ResponseBody
    public String listAuthors() {
        return this.slowService.slowMethod();
    }


    private String dummyHelper(){
        return "Dummy Helper helps Slow Method this time!";
    }
}
