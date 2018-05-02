package com.przemyslawjakubowski.player;

import com.przemyslawjakubowski.board.boardExceptions.IncorrectSymbolException;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.mainStates.InitialState;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Players {
    Queue<Player> players;
    Player startingPlayer;
    Player nextRoundStartingPlayer;

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

    public Player getPlayerByName(String name){
        String trimmedName = name.trim();

        // todo 17.05 add throwing exception in case of incorrect name

        Player current = getNextPlayer();
        if(current.getName().equals(trimmedName)){
            return current;
        }
        return getNextPlayer();
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
        return getPlayerByName(startingPlayer.getName());
    }

    public void setStartingPlayer(Player player){
        startingPlayer = getPlayerByName(player.getName());
        nextRoundStartingPlayer = getNextPlayer();
        getNextPlayer();
    }

    public void setStartingPlayerForNextRound(){
        Player temp = startingPlayer;
        startingPlayer = nextRoundStartingPlayer;
        nextRoundStartingPlayer = temp;
    }
}
