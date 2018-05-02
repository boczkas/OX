package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.player.Point;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameFinishedState implements GameState{
    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {
        Players players = xoGame.getPlayers();

        Player firstPlayer = players.getNextPlayer();
        Player secondPlayer = players.getNextPlayer();

        Point firstPlayerScore = firstPlayer.getScore();
        Point secondPlayerScore = secondPlayer.getScore();

        printScoreStatus(firstPlayer, secondPlayer, output);

        if(firstPlayerScore.getValue() > secondPlayerScore.getValue()){
            printGratulationString(output, firstPlayer);
        }
        else if(firstPlayerScore.getValue() < secondPlayerScore.getValue()){
            printGratulationString(output, secondPlayer);
        }
        else{
            printTieString(output);
        }
    }

    private void printScoreStatus(Player firstPlayer, Player secondPlayer, Consumer<String> output) {
        output.accept("Gracz: " + firstPlayer.getName() + " ilość punktów: " + firstPlayer.getScore().getValue() + "\n" +
                         "Gracz: " + secondPlayer.getName() + " ilość punktów: " + secondPlayer.getScore().getValue() + "\n");

    }

    private void printTieString(Consumer<String> output) {
        output.accept("!!!!!!!!!!!!!!!  REMIS  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                         "???????????????  REMIS  ??????????????????????????????\n");
    }

    private void printGratulationString(Consumer<String> output, Player player) {
        output.accept("!!!!!!!!!!!!!!!  GRATULACJE  !!!!!!!!!!!!!!!!!!!!!!!!!!\n" +
                          "Wygyrywa gracz: " + player.getName() + "\n" +
                          "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }

    @Override
    public GameState goToNextState() {
        return new InitialState();
    }
}
