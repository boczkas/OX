package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.print.Printer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOngoingState implements GameState {

    @Override
    public void performAction(Supplier<String> userInput, OutputConsumer output, XOGame xoGame) {

        BoardStatus boardStatus = xoGame.getBoardStatus();
        MovesHandler movesHandler = new MovesHandler(boardStatus);
        Players players = xoGame.getPlayers();

        Judge judge = new Judge(boardStatus, xoGame.getSymbolsToWin());
        Player player = players.getStartingPlayer();

        EndRequest endRequest = EndRequest.NO;
        while(!(judge.isWinnerPresent() || judge.isTie()|| endRequest.equals(EndRequest.YES))){
            Printer.printBoard(boardStatus, output);
            endRequest = movesHandler.handleMoves(userInput, output, player, judge);
            player = players.getNextPlayer();
        }

        if(endRequest.equals(EndRequest.NO)){
            printWinningMessage(player, judge, output, boardStatus);
            addPointsFromRound(players, judge.isWinnerPresent());
            players.setStartingPlayerForNextRound();
        }

        xoGame.setEndRequest(endRequest);
    }

    private void addPointsFromRound(Players players, boolean isWinnerPresent) {
        Player player = players.getNextPlayer();

        if(isWinnerPresent){
            player.increaseScoreForWin();
        }
        else{
            player.increaseScoreForTie();
            player = players.getNextPlayer();
            player.increaseScoreForTie();
        }
    }

    private void printWinningMessage(Player player, Judge judge, OutputConsumer output, BoardStatus boardStatus) {
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
