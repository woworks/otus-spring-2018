package ru.otus.hw21springacl.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw21springacl.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@CrossOrigin
public class UserController {

    @RequestMapping("/authenticate")
    public User authenticate(@RequestBody User user) {
        if (user.getUsername().equals("user") && user.getPassword().equals("password")) {
            return user;
        }

        return null;
    }
}
