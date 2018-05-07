package com.przemyslawjakubowski.board;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.BoardDimensionException;
import com.przemyslawjakubowski.mainStates.GameConfigurationState;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.output.OutputConsumer;

import java.util.function.Supplier;

public class BoardRowsConfigurationState implements GameConfigurationState {
    boolean isRowConfigurationSuccessful;

    @Override
    public void performAction(Supplier<String> userInput, OutputConsumer output, XOGame xoGame) {
        BoardStatus boardStatus = xoGame.getBoardStatus();
        isRowConfigurationSuccessful = true;
        setBoardRows(userInput, output, boardStatus);
    }

    private void setBoardRows(Supplier<String> userInput, OutputConsumer output, BoardStatus boardStatus) {
        output.accept("A plansza to jaka długa ma być?");
        BoardConfiguration boardConfiguration = new BoardConfiguration();
        int userRowsEntry = tryToSetConfiguration(userInput, output, boardConfiguration);

        if(isRowConfigurationSuccessful){
            boardStatus.setBoardRows(userRowsEntry, output);
        }
    }

    private int tryToSetConfiguration(Supplier<String> userInput, OutputConsumer output, BoardConfiguration boardConfiguration) {
        int userRowsEntry = 0;
        try{
            userRowsEntry = Integer.parseInt(askUserForInput(userInput));
            boardConfiguration.setRows(userRowsEntry);
        } catch (BoardDimensionException e) {
            output.accept(e.toString());
            isRowConfigurationSuccessful = false;
        } catch (NumberFormatException e){
            output.accept("Niepoprawna wartość");
            isRowConfigurationSuccessful = false;
        }
        return userRowsEntry;
    }

    @Override
    public GameState goToNextState() {
        if(isRowConfigurationSuccessful){
            return new BoardColumnsConfigurationState();
        }
        return new BoardRowsConfigurationState();
    }

    @Override
    public String askUserForInput(Supplier<String> userInput) {
        return userInput.get().trim();
    }
}
