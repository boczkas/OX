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

        Player player = players.getFirstPlayer();
        while(true){
            board.print();
            board.handleMoves(userInput, output, player);
            player = players.getOppositePlayer(player);
        }
    }

    @Override
    public GameState goToNextState() {
        System.out.println();
        return new GameFinishedState();
    }
}
