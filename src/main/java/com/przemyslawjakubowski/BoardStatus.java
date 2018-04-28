package com.przemyslawjakubowski;

import com.przemyslawjakubowski.player.Symbol;

import java.util.*;

public class BoardStatus {
    private final int rows;
    private final int columns;
    private Map<Coordinate, Symbol> symbolsAtCoordinates;

    public BoardStatus(int rows, int columns) {
        this.symbolsAtCoordinates = new HashMap<>();
        this.rows = rows;
        this.columns = columns;
    }

    public void addSymbolAtPosition(final Symbol symbol, final Coordinate coordinate) {
        symbolsAtCoordinates.put(coordinate, symbol);
    }

    public boolean checkIfFieldIsEmpty(final Coordinate coordinate) {
        if(symbolsAtCoordinates.containsKey(coordinate)){
            return false;
        }

        return true;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Optional<Symbol> getSymbolAtCoordinate(final Coordinate coordinate){

        if(symbolsAtCoordinates.containsKey(coordinate))
            return Optional.of(symbolsAtCoordinates.get(coordinate));

        return Optional.empty();
    }

    public Map<Coordinate, Symbol> getSymbolsAtCoordinates() {
        return Collections.unmodifiableMap(symbolsAtCoordinates);
    }

    Map<Coordinate, Symbol> getElementsInRow(int row){
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

        while (row < rows && column < columns){ // todo przemienic na zaczytywanie z konfiguracji
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

        while (row >= 0 && column < columns){
            addElementToDiagonal(elementsInDiagonal, row, column);
            row--;
            column++;
        }

        row = currentCoordinate.getX();
        column = currentCoordinate.getY();

        while (row < rows && column >= 0){
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
}
