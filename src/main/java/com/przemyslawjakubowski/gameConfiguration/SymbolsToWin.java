package com.przemyslawjakubowski.gameConfiguration;

import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectAmountOfSymbolsToWinException;

public class SymbolsToWin {
    int symbolsToWin;
    BoardStatus boardStatus;

    public SymbolsToWin(){
        this(0, new BoardStatus());
    }

    public SymbolsToWin(BoardStatus boardStatus) {
        this(0, boardStatus);
    }

    public SymbolsToWin(int symbolsToWin, BoardStatus boardStatus) {
        this.symbolsToWin = symbolsToWin;
        this.boardStatus = boardStatus;
    }


    public int getAmountOfSymbolsToWin() {
        return symbolsToWin;
    }

    public void setSymbolsToWin(int symbolsToWin) throws IncorrectAmountOfSymbolsToWinException {
        if(symbolsToWin > 0 && (symbolsToWin <= boardStatus.getRows() || symbolsToWin <= boardStatus.getColumns())){
            this.symbolsToWin = symbolsToWin;
        }
        else{
            throw new IncorrectAmountOfSymbolsToWinException(symbolsToWin, boardStatus);
        }
    }
}
