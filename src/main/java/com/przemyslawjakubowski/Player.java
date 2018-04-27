package com.przemyslawjakubowski;

public class Player {
    private String name;
    private int score;

    private final Symbol symbol;


    public Player(String name, Symbol symbol) {
        this.name = name;
        this.score = 0;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}