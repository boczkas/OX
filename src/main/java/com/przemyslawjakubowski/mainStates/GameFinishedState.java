package com.przemyslawjakubowski.mainStates;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;
import com.przemyslawjakubowski.output.ReplacePattern;
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
        output.accept(OutputOption.PLAYER_SCORE, new ReplacePattern("%playerName%", firstPlayer.getName()),
                                                 new ReplacePattern("%playerScore%", String.valueOf(firstPlayer.getScore().getValue())));

        output.accept(OutputOption.PLAYER_SCORE, new ReplacePattern("%playerName%", secondPlayer.getName()),
                                                 new ReplacePattern("%playerScore%", String.valueOf(secondPlayer.getScore().getValue())));
    }

    private void printTieString(OutputConsumer output) {
        output.accept("!!!!!!!!!!!!!!!  ");
        output.accept(OutputOption.TIE);
        output.accept("   !!!!!!!!!!!!!!!\n");
    }

    private void printResultStringWhenWinnerIsPresent(OutputConsumer output, Player winner, Player loser) {
        output.accept("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        output.accept(OutputOption.WON, new ReplacePattern("%winnerSymbol%", winner.getSymbol().toString()),
                                        new ReplacePattern("%winnerScore%", String.valueOf(winner.getScore().getValue())),
                                        new ReplacePattern("%loserSymbol%", loser.getSymbol().toString()),
                                        new ReplacePattern("%loserScore%", String.valueOf(loser.getScore().getValue())));
        output.accept("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
    }

    @Override
    public GameState goToNextState() {
        System.exit(0);
        return new InitialState();
    }
}
