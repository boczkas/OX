package com.przemyslawjakubowski;

import com.przemyslawjakubowski.board.BoardConfiguration;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectPointsForTieException;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectPointsForWonException;
import com.przemyslawjakubowski.gameConfiguration.PointsConfiguration;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWin;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.player.Point;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.mainStates.InitialState;

import java.util.function.Supplier;

public class XOGame {

    private final Supplier<String> userInputSupplier;
    private final OutputConsumer output;
    private GameState currentGameState;
    private BoardStatus boardStatus;
    private Players players;
    private PointsConfiguration configuration;
    private SymbolsToWin symbolsToWin;
    private int roundsCounter;
    private EndRequest endRequest;


    public XOGame(Supplier<String> userInputSupplier, OutputConsumer output) {
        this.userInputSupplier = userInputSupplier;
        this.currentGameState = new InitialState();
        this.output = output;
        this.players = new Players();
        this.configuration = new PointsConfiguration();
        this.roundsCounter = 0;
        this.boardStatus = new BoardStatus(new BoardConfiguration());
        this.endRequest = EndRequest.NO;
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

    public void setAmountOfPointsForWinRound(Point pointsForWonRound) throws IncorrectPointsForWonException {
        configuration.setPointsForWonRound(pointsForWonRound);
    }


    public void setAmountOfPointsForTieRound(Point pointsForTie) throws IncorrectPointsForTieException {
        configuration.setPointsForTieRound(pointsForTie);
    }

    public void setSymbolsToWin(SymbolsToWin symbolsToWin) {
        this.symbolsToWin = symbolsToWin;
    }

    public BoardStatus getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(BoardStatus boardStatus) {
        this.boardStatus = boardStatus;
    }

    public SymbolsToWin getSymbolsToWin() {
        return symbolsToWin;
    }

    public void increaseRoundsCounter(){
        this.roundsCounter++;
    }

    public int getRoundNumber(){
        return roundsCounter;
    }

    public EndRequest getEndRequest() {
        return endRequest;
    }

    public void setEndRequest(EndRequest endRequest) {
        this.endRequest = endRequest;
    }
}
