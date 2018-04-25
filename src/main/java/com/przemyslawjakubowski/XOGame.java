package com.przemyslawjakubowski;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class XOGame {

    private final Supplier<String> userInputSupplier;
    private final Consumer<String> output;
    private GameState currentGameState;
    private List<Player> players;

    public XOGame(Supplier<String> userInputSupplier, Consumer<String> output) {
        this.userInputSupplier = userInputSupplier;
        this.currentGameState = new InitialState();
        this.output = output;
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public void addPlayer(Player player){
        if(currentGameState.getClass().equals(InitialState.class)){
            players.add(player);
        }
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
}
