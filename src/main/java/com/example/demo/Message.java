package org.example;


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
