package com.przemyslawjakubowski.board;

import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.BoardDimensionException;

public class BoardConfiguration {
    int rows;
    int columns;

    public BoardConfiguration() {
        this.rows = 0;
        this.columns = 0;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) throws BoardDimensionException {
        if(rows > 0){
            this.rows = rows;
        }
        else{
            throw new BoardDimensionException();
        }
    }

    public void setColumns(int columns) throws BoardDimensionException {
        if(columns > 0){
            this.columns = columns;
        }
        else{
            throw new BoardDimensionException();
        }
    }
}
