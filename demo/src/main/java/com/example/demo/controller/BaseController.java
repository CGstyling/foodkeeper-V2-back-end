package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    //    http://localhost:8080/hello?name=merlin
    @GetMapping("/hello")
    public static String hello(@RequestParam(required = false) String name) {
        if (name == null) {
            return "Hello World";
        } else {
            return "Hello " + name;
        }
    }

    @GetMapping("/christina")
    public static String helloChristina() {
        return "Hello Christina";
    }

    @GetMapping("/info")
    public static String info() {
        return "This is a information page";
    }

    private static String[] questions = {
            "Where is Amsterdam?",
            "Where is New York"
    };

    private static String[] answers = {
            "In the Netherlands",
            "In USA"
    };

    @GetMapping("/questions")
    public String[] getQuestions() {
        return questions;
    }

    @GetMapping("/questions/{nr}")
    public String getQuestion(@PathVariable int nr) {
        return questions[nr];
    }

    @GetMapping("/questions/{nr}/answer")
    public String getAnswer(@PathVariable int nr) {
        return answers[nr];
    }

}
