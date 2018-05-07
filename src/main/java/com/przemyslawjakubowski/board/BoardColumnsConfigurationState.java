package com.przemyslawjakubowski.board;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWinConfigurationState;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.BoardDimensionException;
import com.przemyslawjakubowski.mainStates.GameConfigurationState;
import com.przemyslawjakubowski.mainStates.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardColumnsConfigurationState implements GameConfigurationState {

    boolean isColumnsConfigurationSuccessful;

    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        BoardStatus boardStatus = xoGame.getBoardStatus();
        isColumnsConfigurationSuccessful = true;
        setBoardColumns(userInput, output, boardStatus);
    }

    private void setBoardColumns(Supplier<String> userInput, Consumer<String> output, BoardStatus boardStatus) {
        output.accept("A plansza to jaka szeroka ma być?");
        BoardConfiguration boardConfiguration = new BoardConfiguration();
        int columns = tryToSetConfiguration(userInput, output, boardConfiguration);

        if(isColumnsConfigurationSuccessful){
            boardStatus.setBoardColumns(columns, output);
        }
    }

    private int tryToSetConfiguration(Supplier<String> userInput, Consumer<String> output, BoardConfiguration boardConfiguration) {
        int userColumnsEntry = 0;
        try{
            userColumnsEntry = Integer.parseInt(askUserForInput(userInput));
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

    @Override
    public String askUserForInput(Supplier<String> userInput) {
        return userInput.get().trim();
    }
}
