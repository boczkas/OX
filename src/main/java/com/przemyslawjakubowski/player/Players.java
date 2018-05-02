package com.przemyslawjakubowski.player;

import com.przemyslawjakubowski.board.boardExceptions.IncorrectSymbolException;
import com.przemyslawjakubowski.states.GameState;
import com.przemyslawjakubowski.states.InitialState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void addPlayer(Player player, GameState currentGameState){
        if(currentGameState.getClass().equals(InitialState.class)){
            players.add(player);
        }
    }

    public Player getOppositePlayer(Player actualPlayer) {
        return players.indexOf(actualPlayer) == 0 ? players.get(1) : players.get(0);
    }

    public Player getPlayerBySymbol(String symbol) throws IncorrectSymbolException {
        String trimmedSymbol = symbol.trim();

        if(!(trimmedSymbol.equals("X") || trimmedSymbol.equals("O"))){
            throw new IncorrectSymbolException(symbol);
        }

        return players.get(0).getSymbol().toString().contains(trimmedSymbol) ? players.get(0) : players.get(1);
    }
}
