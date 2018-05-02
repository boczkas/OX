package com.przemyslawjakubowski.gameConfiguration;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectAmountOfSymbolsToWinException;
import com.przemyslawjakubowski.player.StartingPlayerConfigurationState;
import com.przemyslawjakubowski.states.GameConfigurationState;
import com.przemyslawjakubowski.states.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SymbolsToWinConfigurationState implements GameConfigurationState {

    boolean isConfiguredCorrectly = true;

    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        SymbolsToWin symbolsToWin = askUserForSymbolsToWin(userInput, output, xoGame.getBoardStatus());
        xoGame.setSymbolsToWin(symbolsToWin);
    }

    private SymbolsToWin askUserForSymbolsToWin(Supplier<String> userInput, Consumer<String> output, BoardStatus boardStatus) {
        output.accept("Ile symboli w rzędzie daje zwycięztwo?");
        String userEntry = askUserForInput(userInput);
        SymbolsToWin symbolsToWin = new SymbolsToWin(boardStatus);

        tryToSetConfiguration(output, userEntry, symbolsToWin);

        return symbolsToWin;
    }

    private void tryToSetConfiguration(Consumer<String> output, String userEntry, SymbolsToWin symbolsToWin) {
        try{
            symbolsToWin.setSymbolsToWin(Integer.parseInt(userEntry));
        } catch (IncorrectAmountOfSymbolsToWinException e) {
            output.accept(e.toString());
            isConfiguredCorrectly = false;
        } catch (NumberFormatException e){
            output.accept("Niepoprawna wartość");
            isConfiguredCorrectly = false;
        }
    }

    @Override
    public GameState goToNextState() {
        if(isConfiguredCorrectly){
            return new StartingPlayerConfigurationState();
        }
        return new SymbolsToWinConfigurationState();
    }

    @Override
    public String askUserForInput(Supplier<String> userInput) {
        return userInput.get();
    }
}
