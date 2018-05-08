package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;
import com.przemyslawjakubowski.output.ReplacePattern;

public class IncorrectAmountOfSymbolsToWinException extends Throwable {
    int symbolsToWin;
    BoardStatus boardStatus;

    public IncorrectAmountOfSymbolsToWinException(int symbolsToWin, BoardStatus boardStatus) {
        this.symbolsToWin = symbolsToWin;
        this.boardStatus = boardStatus;
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_INCORRECT_AMOUNT_OF_SYMBOLS_TO_WIN, new ReplacePattern("%rows%", String.valueOf(boardStatus.getRows())),
                                                                                         new ReplacePattern("%columns%", String.valueOf(boardStatus.getColumns())),
                                                                                         new ReplacePattern("%symbolsToWin%", String.valueOf(symbolsToWin)));
        outputConsumer.accept("=============================================\n");
    }

    @Override
    public String toString() {
        return "==================================================\n" +
                "Nie da się wygrać na tablicy " + boardStatus.getRows() + "x" + boardStatus.getColumns() + "\n" +
                "przy pomocy " + symbolsToWin + " symboli" + "\n" +
                "=================================================\n";
    }
}
