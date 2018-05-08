package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.userInput.UserInputProvider;

import java.util.function.Supplier;

public interface GameState {
    void performAction(UserInputProvider userInput, OutputConsumer output, XOGame xoGame);
    GameState goToNextState();
}
