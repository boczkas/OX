package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.textOutput.ReplacePattern;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.print.Printer;
import com.przemyslawjakubowski.userInput.UserInputProvider;

import java.util.function.Supplier;

public class GameOngoingState implements GameState {

    @Override
    public void performAction(UserInputProvider userInput, OutputConsumer output, XOGame xoGame) {

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
            addPointsFromRound(players, judge.isWinnerPresent());
            printWinningMessage(players, judge, output, boardStatus);
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

    private void printWinningMessage(Players players, Judge judge, OutputConsumer output, BoardStatus boardStatus) {
        Printer.printBoard(boardStatus, output);
        Player loser = players.getNextPlayer();
        Player winner = players.getNextPlayer();

        if(judge.isWinnerPresent()){
            output.accept(OutputOption.ROUND_WON, new ReplacePattern("%winnerSymbol%", winner.getSymbol().toString()),
                                                  new ReplacePattern("%winnerScore%", winner.getScore().toString()),
                                                  new ReplacePattern("%loserSymbol%", loser.getSymbol().toString()),
                                                  new ReplacePattern("%loserScore%", loser.getScore().toString()));
        }
        else{
            output.accept(OutputOption.TIE);
        }
    }

    @Override
    public GameState goToNextState() {
        return new RoundFinishedState();
    }
}
