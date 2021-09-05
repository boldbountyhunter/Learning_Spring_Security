package com.example.springSecurity.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearnSpringSecurityResource {

    @GetMapping(value = "/")
    public String sayHello() {
        return "Hello Saurav";
    }

    @GetMapping(value = "/user")
    public String sayHi() {
        return "Hi Saurav";
    }

    @GetMapping(value = "/admin")
    public String sayHey() {
        return "Heyy Saurav";
    }
}
