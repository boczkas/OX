package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.player.Point;

import java.util.function.Supplier;

public class GameFinishedState implements GameState{
    @Override
    public void performAction(Supplier<String> userInput, OutputConsumer output, XOGame xoGame) {
        Players players = xoGame.getPlayers();

        Player firstPlayer = players.getNextPlayer();
        Player secondPlayer = players.getNextPlayer();

        Point firstPlayerScore = firstPlayer.getScore();
        Point secondPlayerScore = secondPlayer.getScore();

        if(firstPlayerScore.getValue() > secondPlayerScore.getValue()){
            printResultStringWhenWinnerIsPresent(output, firstPlayer, secondPlayer);
        }
        else if(firstPlayerScore.getValue() < secondPlayerScore.getValue()){
            printResultStringWhenWinnerIsPresent(output, secondPlayer, firstPlayer);
        }
        else{
            printScoreStatus(firstPlayer, secondPlayer, output);
            printTieString(output);
        }
    }

    private void printScoreStatus(Player firstPlayer, Player secondPlayer, OutputConsumer output) {
        output.accept("Gracz: " + firstPlayer.getName() + " ilość punktów: " + firstPlayer.getScore().getValue() + "\n" +
                         "Gracz: " + secondPlayer.getName() + " ilość punktów: " + secondPlayer.getScore().getValue() + "\n");

    }

    private void printTieString(OutputConsumer output) {
        output.accept("!!!!!!!!!!!!!!!  REMIS  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                         "???????????????  REMIS  ??????????????????????????????\n");
    }

    private void printResultStringWhenWinnerIsPresent(OutputConsumer output, Player winner, Player loser) {
        output.accept("!!!!!!!!!!!!!!!  GRATULACJE  !!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                          "Wygyrywa: " + winner.getSymbol() + ". " + winner.getSymbol() +
                          ": " + winner.getScore().getValue() + " " +
                          loser.getSymbol() + ": " + loser.getScore().getValue() + "\n" +
                          "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }

    @Override
    public GameState goToNextState() {
        System.exit(0);
        return new InitialState();
    }
}
