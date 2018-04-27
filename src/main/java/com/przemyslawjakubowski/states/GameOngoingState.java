package com.przemyslawjakubowski.states;

import com.przemyslawjakubowski.*;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOngoingState implements GameState {


    @Override
    public void performAction(Supplier<String> userInput, Consumer<String> output, XOGame xoGame) {

        output.accept("A tablica to jaka wielka ma być?");

        Board board = new Board(new BoardStatus(3,3));
        Players players = xoGame.getPlayers();
        output.accept("Gramy PAAAANIE!");
        output.accept("Kto zaczyna?");

        Player player;

        player = getStartingPlayer(userInput, output, players);

        while(true){
            board.print();
            board.handleMoves(userInput, output, player);
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
