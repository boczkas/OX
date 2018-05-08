package com.przemyslawjakubowski.textOutput;

public class ReplacePattern {
    String pattern;
    String newValue;

    public ReplacePattern(String pattern, String newValue) {
        this.pattern = pattern;
        this.newValue = newValue;
    }

    public String getPattern() {
        return pattern;
    }

    public String getNewValue() {
        return newValue;
    }
}
