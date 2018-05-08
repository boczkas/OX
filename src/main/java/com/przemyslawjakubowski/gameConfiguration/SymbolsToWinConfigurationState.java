package com.przemyslawjakubowski.gameConfiguration;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectAmountOfSymbolsToWinException;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.player.StartingPlayerConfigurationState;
import com.przemyslawjakubowski.mainStates.GameConfigurationState;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.userInput.UserInputProvider;

import java.util.function.Supplier;

public class SymbolsToWinConfigurationState implements GameConfigurationState {

    boolean isConfiguredCorrectly = true;

    @Override
    public void performAction(UserInputProvider userInput, OutputConsumer output, XOGame xoGame) {
        SymbolsToWin symbolsToWin = askUserForSymbolsToWin(userInput, output, xoGame.getBoardStatus());
        xoGame.setSymbolsToWin(symbolsToWin);
    }

    private SymbolsToWin askUserForSymbolsToWin(UserInputProvider userInput, OutputConsumer output, BoardStatus boardStatus) {
        output.accept(OutputOption.SYMBOLS_AMOUNT_QUESTION);
        String userEntry = askUserForInput(userInput);
        SymbolsToWin symbolsToWin = new SymbolsToWin(boardStatus);

        tryToSetConfiguration(output, userEntry, symbolsToWin);

        return symbolsToWin;
    }

    private void tryToSetConfiguration(OutputConsumer output, String userEntry, SymbolsToWin symbolsToWin) {
        try{
            symbolsToWin.setSymbolsToWin(Integer.parseInt(userEntry));
        } catch (IncorrectAmountOfSymbolsToWinException e) {
            e.printExceptionMessage(output);
            isConfiguredCorrectly = false;
        } catch (NumberFormatException e){
            output.accept(OutputOption.INCORRECT_VALUE);
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
    public String askUserForInput(UserInputProvider userInput) {
        return userInput.get();
    }
}
