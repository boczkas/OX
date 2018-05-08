package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.userInput.UserInputProvider;


public interface GameConfigurationState extends GameState {

    String askUserForInput(UserInputProvider userInput);

}
