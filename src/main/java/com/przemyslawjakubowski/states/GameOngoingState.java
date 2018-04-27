package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.boardExceptions.IncorrectSymbolException;
import com.przemyslawjakubowski.print.Printer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOngoingState implements GameState {

    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {

        output.accept("A tablica to jaka wielka ma być?");

        BoardStatus boardStatus = new BoardStatus(3,3);
        MovesHandler movesHandler = new MovesHandler(boardStatus);
        Players players = xoGame.getPlayers();
        output.accept("Gramy PAAAANIE!");
        output.accept("Kto zaczyna?");

        Player player;
        Judge judge = new Judge(boardStatus, 3); // todo zmienic na konfigurowalna wartosc
        player = getStartingPlayer(userInput, output, players);

        while(!judge.isWinnerPresent()){
            Printer.print(boardStatus);
            movesHandler.handleMoves(userInput, output, player, judge);
            player = players.getOppositePlayer(player);
        }
    }

    private Player getStartingPlayer(Supplier<String> userInput, Consumer<String> output, Players players) {
        Player player = null;
        try {
            output.accept("Ktory gracz zaczyna? X czy O?");
            player = players.getPlayerBySymbol(userInput.get());
        } catch (IncorrectSymbolException exception){
            output.accept(exception.toString());
            getStartingPlayer(userInput, output, players);
        }
        return player;
    }

    @Override
    public GameState goToNextState() {
        return new GameFinishedState();
    }
}
