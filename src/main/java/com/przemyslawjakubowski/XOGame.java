package com.przemyslawjakubowski;

import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.states.GameState;
import com.przemyslawjakubowski.states.InitialState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class    XOGame {

    private final Supplier<String> userInputSupplier;
    private final Consumer<String> output;
    private GameState currentGameState;
    private Players players;

    public XOGame(Supplier<String> userInputSupplier, Consumer<String> output) {
        this.userInputSupplier = userInputSupplier;
        this.currentGameState = new InitialState();
        this.output = output;
        this.players = new Players();
    }

    public void start() {
        while (true){
            makeOneStepOfGame();
        }
    }

    private void makeOneStepOfGame() {
        this.currentGameState.performAction(userInputSupplier, output, this);
        this.currentGameState = currentGameState.goToNextState();
    }

    public void addPlayer(Player player) {
        players.addPlayer(player, currentGameState);
    }

    public Players getPlayers(){
        return players;
    }
}
