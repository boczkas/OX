package com.przemyslawjakubowski.output;

public class ReplacePattern {
    String pattern;
    String replaceWith;

    public ReplacePattern(String pattern, String replaceWith) {
        this.pattern = pattern;
        this.replaceWith = replaceWith;
    }

    public String getPattern() {
        return pattern;
    }

    public String getReplaceWith() {
        return replaceWith;
    }
}
