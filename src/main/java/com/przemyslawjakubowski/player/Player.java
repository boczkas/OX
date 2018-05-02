package com.przemyslawjakubowski.player;

import java.util.Objects;

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

    public Point getScore(){
        return score;
    }

    public void increaseScoreForWin(){
        this.score.increaseValue(3);
    }

    public void increaseScoreForTie(){
        this.score.increaseValue(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}