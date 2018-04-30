package com.przemyslawjakubowski.gameConfiguration;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectAmountOfSymbolsToWinException;
import com.przemyslawjakubowski.player.StartingPlayerConfigurationState;
import com.przemyslawjakubowski.states.GameOngoingState;
import com.przemyslawjakubowski.states.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SymbolsToWinConfigurationState implements GameState {

    boolean isConfiguredCorrectly = true;

    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        SymbolsToWin symbolsToWin = getAmountOfSymbolsToWin(userInput, output, xoGame.getBoardStatus());
        xoGame.setSymbolsToWin(symbolsToWin);
    }

    private SymbolsToWin getAmountOfSymbolsToWin(Supplier<String> userInput, Consumer<String> output, BoardStatus boardStatus) {
        output.accept("Ile symboli w rzędzie daje zwycięztwo?");
        String userEntry = userInput.get();
        SymbolsToWin symbolsToWin = new SymbolsToWin(boardStatus);

        try{
            symbolsToWin.setSymbolsToWin(Integer.parseInt(userEntry));
        } catch (IncorrectAmountOfSymbolsToWinException e) {
            output.accept(e.toString());
            isConfiguredCorrectly = false;
        }

        return symbolsToWin;
    }

    @Override
    public GameState goToNextState() {
        if(isConfiguredCorrectly){
            return new StartingPlayerConfigurationState();
        }
        return new SymbolsToWinConfigurationState();
    }
}
