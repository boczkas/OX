package com.przemyslawjakubowski.player;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.boardExceptions.IncorrectSymbolException;
import com.przemyslawjakubowski.mainStates.GameConfigurationState;
import com.przemyslawjakubowski.mainStates.GameOngoingState;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.userInput.UserInputProvider;

import java.util.function.Supplier;

public class StartingPlayerConfigurationState implements GameConfigurationState {

    boolean startingPlayerSetCorrectly = true;

    @Override
    public void performAction(UserInputProvider userInput, OutputConsumer output, XOGame xoGame) {

        tryToSetConfiguration(userInput, output, xoGame);
    }

    private void tryToSetConfiguration(UserInputProvider userInput, OutputConsumer output, XOGame xoGame) {
        try {
            Players players = xoGame.getPlayers();
            output.accept(OutputOption.STARTING_PLAYER_QUESTION);
            Player player = players.getPlayerBySymbol(askUserForInput(userInput));
            players.setStartingPlayer(player);
        } catch (IncorrectSymbolException e){
            e.printExceptionMessage(output);
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
    public String askUserForInput(UserInputProvider userInput) {
        return userInput.get();
    }
}
