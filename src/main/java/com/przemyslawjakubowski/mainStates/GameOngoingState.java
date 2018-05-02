package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.board.BoardStatus;
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

        EndRequest endRequest = EndRequest.NO;
        while(!(judge.isWinnerPresent() || judge.checkTie()|| endRequest.equals(EndRequest.YES))){
            Printer.printBoard(boardStatus, output);
            endRequest = movesHandler.handleMoves(userInput, output, player, judge);
            player = players.getNextPlayer();
        }

        if(endRequest.equals(EndRequest.NO)){
            printWinningMessage(player, judge, output, boardStatus);
            addPointsFromRound(players, judge);
        }

        xoGame.setEndRequest(endRequest);
    }

    private void addPointsFromRound(Players players, Judge judge) {
        Player winner = players.getNextPlayer();

        if(judge.isWinnerPresent()){
            winner.increaseScoreForWin();
        }
        else{
            winner.increaseScoreForTie();
            Player loser = players.getNextPlayer();
            loser.increaseScoreForTie();
        }
    }

    private void printWinningMessage(Player player, Judge judge, Consumer<String> output, BoardStatus boardStatus) {
        Printer.printBoard(boardStatus, output);
        if(judge.isWinnerPresent()){
            output.accept("Rundę wygrywa gracz: " + player.getName() + " !\n");
        }
        else{
            output.accept("Remis!\n");
        }
    }

    @Override
    public GameState goToNextState() {
        return new RoundFinishedState();
    }
}
