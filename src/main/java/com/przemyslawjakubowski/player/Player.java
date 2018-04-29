package com.przemyslawjakubowski.player;

public class Player {
    private String name;
    private Point score;

    private final Symbol symbol;


    public Player(String name, Symbol symbol, Point score) {
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