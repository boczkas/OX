package com.przemyslawjakubowski;

public class Player {
    private String name;
    private Score score;

    private final Symbol symbol;


    public Player(String name, Symbol symbol, Score score) {
        this.name = name;
        this.symbol = symbol;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}