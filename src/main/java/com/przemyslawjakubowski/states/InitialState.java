package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectPointsForTieException;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectPointsForWonException;
import com.przemyslawjakubowski.player.Point;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Symbol;
import com.przemyslawjakubowski.XOGame;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {
    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        try{
            xoGame.setAmountOfPointsForWinRound(new Point(3));
            xoGame.setAmountOfPointsForTieRound(new Point(1));
        }
        catch (IncorrectPointsForTieException|IncorrectPointsForWonException e){
            output.accept(e.toString());
        }

        output.accept("Bedziem zaczynac!\n");
        output.accept("Podaj imię pierwszego gracza (X)!\n");
        xoGame.addPlayer(new Player(userInput.get(), Symbol.X, new Point(0)));
        output.accept("Podaj imię drugiego gracza (O)!\n");
        xoGame.addPlayer(new Player(userInput.get(), Symbol.O, new Point(0)));

    }

    @Override
    public GameState goToNextState() {
        return new GameOngoingState();
    }
}
