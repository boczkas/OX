package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.BoardStatus;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RoundFinishedState implements GameState {
    int roundNumber;

    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        BoardStatus boardStatus = xoGame.getBoardStatus();
        boardStatus.resetBoardSymbols();
        xoGame.increaseRoundsCounter();
        roundNumber = xoGame.getRoundNumber();
    }

    @Override
    public GameState goToNextState() {
        if(roundNumber < 3){
            return new GameOngoingState();
        }
        return new GameFinishedState();
    }
}
