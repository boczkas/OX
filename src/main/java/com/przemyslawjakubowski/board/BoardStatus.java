package com.przemyslawjakubowski.board;

import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.BoardDimensionException;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.player.Symbol;

import java.util.*;

public class BoardStatus {
    BoardConfiguration boardConfiguration;
    private Map<Coordinate, Symbol> symbolsAtCoordinates;

    public BoardStatus(BoardConfiguration boardConfiguration) {
        this.symbolsAtCoordinates = new HashMap<>();
        this.boardConfiguration = boardConfiguration;
    }

    public void addSymbolAtCoordinate(final Symbol symbol, final Coordinate coordinate) {
        symbolsAtCoordinates.put(coordinate, symbol);
    }

    public boolean checkIfFieldIsEmpty(final Coordinate coordinate) {
        if(symbolsAtCoordinates.containsKey(coordinate)){
            return false;
        }

        return true;
    }

    public int getRows() {
        return boardConfiguration.getRows();
    }

    public int getColumns() {
        return boardConfiguration.getColumns();
    }

    public Optional<Symbol> getSymbolAtCoordinate(final Coordinate coordinate){

        if(symbolsAtCoordinates.containsKey(coordinate))
            return Optional.of(symbolsAtCoordinates.get(coordinate));

        return Optional.empty();
    }

    public Map<Coordinate, Symbol> getSymbolsAtCoordinates() {
        return Collections.unmodifiableMap(symbolsAtCoordinates);
    }

    public Map<Coordinate, Symbol> getElementsInRow(int row){
        Map<Coordinate, Symbol> elementsInRow = new HashMap<>();

        for(Coordinate coordinate : symbolsAtCoordinates.keySet()){
            if(coordinate.getX() == row){
                elementsInRow.put(coordinate, symbolsAtCoordinates.get(coordinate));
            }
        }
        return elementsInRow;
    }

    public Map<Coordinate, Symbol> getElementsInColumn(int column) {
        Map<Coordinate, Symbol> elementsInColumn = new HashMap<>();

        for(Coordinate coordinate : symbolsAtCoordinates.keySet()){
            if(coordinate.getY() == column){
                elementsInColumn.put(coordinate, symbolsAtCoordinates.get(coordinate));
            }
        }
        return elementsInColumn;
    }

    public Map<Coordinate,Symbol> getElementsInLeftDiagonal(Coordinate currentCoordinate) {
        Map<Coordinate, Symbol> elementsInDiagonal = new HashMap<>();

        int row = currentCoordinate.getX();
        int column = currentCoordinate.getY();

        while(row >= 0 && column >= 0){
            addElementToDiagonal(elementsInDiagonal, row, column);
            row--;
            column--;
        }

        row = currentCoordinate.getX();
        column = currentCoordinate.getY();

        while (row < boardConfiguration.getRows() && column < boardConfiguration.getColumns()){
            addElementToDiagonal(elementsInDiagonal, row, column);
            row++;
            column++;
        }

        return elementsInDiagonal;
    }

    public Map<Coordinate,Symbol> getElementsInRightDiagonal(Coordinate currentCoordinate) {
        Map<Coordinate, Symbol> elementsInDiagonal = new HashMap<>();
        int row = currentCoordinate.getX();
        int column = currentCoordinate.getY();

        while (row >= 0 && column < boardConfiguration.getColumns()){
            addElementToDiagonal(elementsInDiagonal, row, column);
            row--;
            column++;
        }

        row = currentCoordinate.getX();
        column = currentCoordinate.getY();

        while (row < boardConfiguration.getColumns() && column >= 0){
            addElementToDiagonal(elementsInDiagonal, row, column);
            row++;
            column--;
        }

        return elementsInDiagonal;
    }

    private void addElementToDiagonal(Map<Coordinate, Symbol> elementsInDiagonal, int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);
        if(symbolsAtCoordinates.containsKey(coordinate)){
            elementsInDiagonal.put(coordinate, symbolsAtCoordinates.get(coordinate));
        }
    }

    public void setBoardRows(int rows, OutputConsumer output) {
        try{
            boardConfiguration.setRows(rows);

        } catch (BoardDimensionException e) {
            e.printExceptionMessage(output);
        }
    }

    public void setBoardColumns(int columns, OutputConsumer output){
        try {
            boardConfiguration.setColumns(columns);
        } catch (BoardDimensionException e) {
            e.printExceptionMessage(output);
        }

    }

    public int getPossibleAmountOfSymbols(){
        return boardConfiguration.getColumns() * boardConfiguration.getRows();
    }

    public void resetBoardSymbols(){
        this.symbolsAtCoordinates = new HashMap<>();
    }
}
