package com.przemyslawjakubowski;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOngoingState implements GameState {
    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output) {
        output.accept("Gramy PAAAANIE!");
    }

    @Override
    public GameState goToNextState() {
        System.out.println();
        return new GameFinishedState();
    }
}
