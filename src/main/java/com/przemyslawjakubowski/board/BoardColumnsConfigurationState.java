package com.przemyslawjakubowski.board;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWinConfigurationState;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.BoardDimensionException;
import com.przemyslawjakubowski.states.GameOngoingState;
import com.przemyslawjakubowski.states.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardColumnsConfigurationState implements GameState {

    boolean isColumnsConfigurationSuccessful;

    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        BoardStatus boardStatus =xoGame.getBoardStatus();
        isColumnsConfigurationSuccessful = true;
        setBoardColumns(userInput, output, boardStatus);
    }

    private void setBoardColumns(Supplier<String> userInput, Consumer<String> output, BoardStatus boardStatus) {
        output.accept("A plansza to jaka szeroka ma być?");

        int columns = getAmountOfColumns(userInput, output);

        if(isColumnsConfigurationSuccessful){
            boardStatus.setBoardColumns(columns, output);
        }
    }

    private int getAmountOfColumns(Supplier<String> userInput, Consumer<String> output) {

        BoardConfiguration boardConfiguration = new BoardConfiguration();

        int userColumnsEntry = 0;
        try{
            userColumnsEntry = Integer.parseInt(userInput.get());
            boardConfiguration.setColumns(userColumnsEntry);
        } catch (BoardDimensionException e) {
            output.accept(e.toString());
            isColumnsConfigurationSuccessful = false;
        } catch (NumberFormatException e){
            output.accept("Niepoprawna wartość");
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
}
