package com.przemyslawjakubowski.board;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWinConfigurationState;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.BoardDimensionException;
import com.przemyslawjakubowski.mainStates.GameConfigurationState;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;

import java.util.function.Supplier;

public class BoardColumnsConfigurationState implements GameConfigurationState {

    boolean isColumnsConfigurationSuccessful;

    @Override
    public void performAction(Supplier<String> userInput, OutputConsumer output, XOGame xoGame) {
        BoardStatus boardStatus = xoGame.getBoardStatus();
        isColumnsConfigurationSuccessful = true;
        setBoardColumns(userInput, output, boardStatus);
    }

    private void setBoardColumns(Supplier<String> userInput, OutputConsumer output, BoardStatus boardStatus) {
        output.accept(OutputOption.WIDTH_QUESTION);
        BoardConfiguration boardConfiguration = new BoardConfiguration();
        int columns = tryToSetConfiguration(userInput, output, boardConfiguration);

        if(isColumnsConfigurationSuccessful){
            boardStatus.setBoardColumns(columns, output);
        }
    }

    private int tryToSetConfiguration(Supplier<String> userInput, OutputConsumer output, BoardConfiguration boardConfiguration) {
        int userColumnsEntry = 0;
        try{
            userColumnsEntry = Integer.parseInt(askUserForInput(userInput));
            boardConfiguration.setColumns(userColumnsEntry);
        } catch (BoardDimensionException e) {
            e.printExceptionMessage(output);
            isColumnsConfigurationSuccessful = false;
        } catch (NumberFormatException e){
            output.accept(OutputOption.INCORRECT_VALUE);
            isColumnsConfigurationSuccessful = false;
        }
        return userColumnsEntry;
    }

    @Override
    public GameState goToNextState() {
        if(isColumnsConfigurationSuccessful){
            return new SymbolsToWinConfigurationState();
        }
        return new BoardColumnsConfigurationState();
    }

    @Override
    public String askUserForInput(Supplier<String> userInput) {
        return userInput.get().trim();
    }
}
