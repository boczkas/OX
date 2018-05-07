package com.przemyslawjakubowski.player;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.boardExceptions.IncorrectSymbolException;
import com.przemyslawjakubowski.mainStates.GameConfigurationState;
import com.przemyslawjakubowski.mainStates.GameOngoingState;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.output.OutputConsumer;

import java.util.function.Supplier;

public class StartingPlayerConfigurationState implements GameConfigurationState {

    boolean startingPlayerSetCorrectly = true;

    @Override
    public void performAction(Supplier<String> userInput, OutputConsumer output, XOGame xoGame) {

        tryToSetConfiguration(userInput, output, xoGame);
    }

    private void tryToSetConfiguration(Supplier<String> userInput, OutputConsumer output, XOGame xoGame) {
        try {
            Players players = xoGame.getPlayers();
            output.accept("Ktory gracz zaczyna? X czy O?");
            Player player = players.getPlayerBySymbol(askUserForInput(userInput));
            players.setStartingPlayer(player);
        } catch (IncorrectSymbolException exception){
            output.accept(exception.toString());
            startingPlayerSetCorrectly = false;
        }
    }

    @Override
    public GameState goToNextState() {
        if(startingPlayerSetCorrectly){
            return new GameOngoingState();
        }
        return new StartingPlayerConfigurationState();
    }

    @Override
    public String askUserForInput(Supplier<String> userInput) {
        return userInput.get();
    }
}
