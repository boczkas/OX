package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.EndRequest;
import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.output.OutputConsumer;

import java.util.function.Supplier;

public class RoundFinishedState implements GameState {
    int roundNumber;
    final int ROUNDS = 3;
    EndRequest endRequest;

    @Override
    public void performAction(Supplier<String> userInput, OutputConsumer output, XOGame xoGame) {
        BoardStatus boardStatus = xoGame.getBoardStatus();
        boardStatus.resetBoardSymbols();
        xoGame.increaseRoundsCounter();
        roundNumber = xoGame.getRoundNumber();
        endRequest = xoGame.getEndRequest();
    }

    @Override
    public GameState goToNextState() {
        if(roundNumber < ROUNDS && endRequest.equals(EndRequest.NO)){
            return new GameOngoingState();
        }
        return new GameFinishedState();
    }
}
