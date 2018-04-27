package com.przemyslawjakubowski;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardStatus {
    final int rows;
    final int columns;
    Map<Coordinate, Symbol> symbolsAtCoordinates;

    public BoardStatus(int rows, int columns) {
        this.symbolsAtCoordinates = new HashMap<>();
        this.rows = rows;
        this.columns = columns;
    }

    public void addSymbolAtPosition(Symbol symbol, Coordinate coordinate) {
        symbolsAtCoordinates.put(coordinate, symbol);
    }

    public boolean checkIfFieldIsEmpty(Coordinate coordinate) {
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

    public Optional<Symbol> getSymbolAtCoordinate(Coordinate coordinate){

        if(symbolsAtCoordinates.containsKey(coordinate))
            return Optional.of(symbolsAtCoordinates.get(coordinate));

        return Optional.empty();
    }
}
