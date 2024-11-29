package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

public class Message {

    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 10;

    public String getMessage(int number) {
        if (isInRange(number)) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private boolean isInRange(int number) {
        return number >= MIN_VALUE && number <= MAX_VALUE;
    }
}
