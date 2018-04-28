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

        int x = currentCoordinate.getX();
        int y = currentCoordinate.getY();

        while(x >= 0 && y >= 0){
            Coordinate coordinate = new Coordinate(x, y);
            elementsInDiagonal.put(coordinate, symbolsAtCoordinates.get(coordinate));
            x--;
            y--;
        }

        x = currentCoordinate.getX();
        y = currentCoordinate.getY();

        while (x < rows && y < columns){ // todo przemienic na zaczytywanie z konfiguracji
            Coordinate coordinate = new Coordinate(x, y);
            elementsInDiagonal.put(coordinate, symbolsAtCoordinates.get(coordinate));
            x++;
            y++;
        }

        return elementsInDiagonal;
    }
}
