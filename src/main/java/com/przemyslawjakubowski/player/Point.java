package com.przemyslawjakubowski.player;

public class Point {
    int value;

    public Point(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void increaseValue(int value){
        this.value += value;
    }

    @Override
    public String toString() {
        return String.valueOf(value) ;
    }
}
