package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.XOGame;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameFinishedState implements GameState {
    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        output.accept("Koniec!");
    }

    @Override
    public GameState goToNextState() {
        System.out.println();
        return new InitialState();
    }
}
