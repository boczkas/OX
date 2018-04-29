package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

import com.przemyslawjakubowski.board.BoardStatus;

public class IncorrectAmountOfSymbolsToWinException extends Throwable {
    int symbolsToWin;
    BoardStatus boardStatus;

    public IncorrectAmountOfSymbolsToWinException(int symbolsToWin, BoardStatus boardStatus) {
        this.symbolsToWin = symbolsToWin;
        this.boardStatus = boardStatus;
    }

    @Override
    public String toString() {
        return "==================================================\n" +
                "Nie da się wygrać na tablicy " + boardStatus.getRows() + "x" + boardStatus.getColumns() + "\n" +
                "przy pomocy " + symbolsToWin + " symboli" + "\n" +
                "=================================================\n";
    }
}
