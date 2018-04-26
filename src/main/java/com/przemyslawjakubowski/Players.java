package com.przemyslawjakubowski;

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
}
