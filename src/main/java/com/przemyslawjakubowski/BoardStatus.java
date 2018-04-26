package com.przemyslawjakubowski;

import java.util.HashMap;
import java.util.Map;

public class BoardStatus {
    final int rows;
    final int columns;
    Map<Coordinate, Symbol> symbolsAtCoordinates;

    public BoardStatus(int rows, int columns) {
        this.symbolsAtCoordinates = new HashMap<>();
        this.rows = rows;
        this.columns = columns;
    }

    public void print() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                Coordinate currentCoordinate = new Coordinate(i, j);
                if(symbolsAtCoordinates.containsKey(currentCoordinate)){
                    stringBuilder.append(symbolsAtCoordinates.get(currentCoordinate));
                }
                else{
                    stringBuilder.append(i + " " + j);
                }
                stringBuilder.append("\t\t");
            }
            stringBuilder.append("\n\n");
        }
        System.out.println(stringBuilder);
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
}
