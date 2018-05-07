package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.XOGame;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GameState {
    void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame);
    GameState goToNextState();
}
