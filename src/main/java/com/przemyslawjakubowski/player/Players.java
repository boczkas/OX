package com.przemyslawjakubowski.player;

import com.przemyslawjakubowski.board.boardExceptions.IncorrectSymbolException;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.mainStates.InitialState;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Players {
    Queue<Player> players;
    Player startingPlayer;

    public Players() {
        this.players = new LinkedBlockingQueue<>();
    }

    public void addPlayer(Player player, GameState currentGameState){
        if(currentGameState.getClass().equals(InitialState.class)){
            players.add(player);
        }
    }

    public Player getNextPlayer() {
        Player current = players.remove();
        players.add(current);
        return current;
    }

    public Player getPlayerBySymbol(String symbol) throws IncorrectSymbolException {
        String trimmedSymbol = symbol.trim();

        if(!(trimmedSymbol.equals("X") || trimmedSymbol.equals("O"))){
            throw new IncorrectSymbolException(symbol);
        }

        Player current = getNextPlayer();
        if(current.getSymbol().toString().contains(trimmedSymbol)){
            return current;
        }
        return getNextPlayer();
    }

    public Player getStartingPlayer() {
        Player currentPlayer = getNextPlayer();

        if(currentPlayer.equals(startingPlayer)){
            return currentPlayer;
        }

        return getNextPlayer();
    }

    void setStartingPlayer(Player player){
        this.startingPlayer = player;
    }
}
