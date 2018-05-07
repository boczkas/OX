package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.output.OutputConsumer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GameState {
    void performAction(Supplier<String> userInput, OutputConsumer output, XOGame xoGame);
    GameState goToNextState();
}
