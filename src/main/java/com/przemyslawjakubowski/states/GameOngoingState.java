package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.board.boardExceptions.IncorrectSymbolException;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.print.Printer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOngoingState implements GameState {

    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {

        BoardStatus boardStatus = xoGame.getBoardStatus();
        MovesHandler movesHandler = new MovesHandler(boardStatus);
        Players players = xoGame.getPlayers();

        Judge judge = new Judge(boardStatus, xoGame.getSymbolsToWin());
        Player player = players.getStartingPlayer();

        while(!judge.isWinnerPresent()){
            Printer.printBoard(boardStatus, output);
            movesHandler.handleMoves(userInput, output, player, judge);
            player = players.getNextPlayer();
        }
    }

    @Override
    public GameState goToNextState() {
        return new GameFinishedState();
    }
}
