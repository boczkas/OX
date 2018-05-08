package com.przemyslawjakubowski.userInput;

import java.util.function.Supplier;

public class UserInputProvider {
    Supplier<String> userInputProvider;

    public UserInputProvider(Supplier<String> userInputProvider) {
        this.userInputProvider = userInputProvider;
    }

    public String get(){
        return userInputProvider.get().trim();
    }
}
