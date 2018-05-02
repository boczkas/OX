package com.przemyslawjakubowski.states;

import java.util.function.Supplier;

public interface GameConfigurationState extends GameState {

    String askUserForInput(Supplier<String> userInput);

}
