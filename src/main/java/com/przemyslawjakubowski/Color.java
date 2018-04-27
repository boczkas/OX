package com.przemyslawjakubowski;

public enum Color {
    ANSI_DEFAULT("\u001B[0m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_CYAN("\u001B[36m");

    private String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
