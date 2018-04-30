package com.przemyslawjakubowski.board;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.BoardDimensionException;
import com.przemyslawjakubowski.states.GameState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardRowsConfigurationState implements GameState{
        boolean isRowConfigurationSuccessful;

        @Override
        public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
            BoardStatus boardStatus = xoGame.getBoardStatus();
            isRowConfigurationSuccessful = true;
            setBoardRows(userInput, output, boardStatus);
        }

        private void setBoardRows(Supplier<String> userInput, Consumer<String> output, BoardStatus boardStatus) {
            int rows = getAmountOfRows(userInput, output);
            if(isRowConfigurationSuccessful){
                boardStatus.setBoardRows(rows, output);
            }
        }

        private int getAmountOfRows(Supplier<String> userInput, Consumer<String> output) {
            output.accept("A plansza to jaka długa ma być?");
            BoardConfiguration boardConfiguration = new BoardConfiguration();

            int userRowsEntry = 0;
            try{
                userRowsEntry = Integer.parseInt(userInput.get());
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
}
