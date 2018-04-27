package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Score;
import com.przemyslawjakubowski.player.Symbol;
import com.przemyslawjakubowski.XOGame;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {
    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        output.accept("Bedziem zaczynac!\n");
        output.accept("Podaj imię pierwszego gracza (X)!\n");
        xoGame.addPlayer(new Player(userInput.get(), Symbol.X, new Score()));
        output.accept("Podaj imię drugiego gracza (O)!\n");
        xoGame.addPlayer(new Player(userInput.get(), Symbol.O, new Score()));
    }

    @Override
    public GameState goToNextState() {
        System.out.println();
        return new GameOngoingState();
    }
}
