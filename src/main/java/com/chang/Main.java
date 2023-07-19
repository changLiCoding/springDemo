package com.chang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//Annotations that wrap annotations like: @ComponentScan, @EnableAutoConfiguration and @Configuration
@SpringBootApplication
@RestController

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
    ArrayList<String> followers = new ArrayList<String>(List.of(new String[]{"Kyle", "Kenney", "Timmy", "Eric"}));

    @GetMapping("/whatever")
    public GreetResponse welcome() {
        GreetResponse response = new GreetResponse("Welcome to Greeting",
                false, followers,
                List.of("Java", "Javascript", "Golong", "Python", "Ruby"),
                new Person("Chang", 30, 9999999.99)
                );
        return response;
    }

    record Person (String name, int age, double saving) {

    }
    record GreetResponse(String greeting,
                         boolean isRegistered,
                         ArrayList<String> followers,
                         List<String> favProgrammingLanguages,
                         Person person) {}

}

