package com.przemyslawjakubowski;

import java.util.Map;
import java.util.Optional;

public class Judge {
    boolean isWinner;
    BoardStatus boardStatus;
    int symbolsToWin;

    public Judge(BoardStatus boardStatus, int symbolsToWin) {
        this.boardStatus = boardStatus;
        this.isWinner = false;
        this.symbolsToWin = symbolsToWin;
    }

    public boolean isWinnerPresent() {
        return isWinner;
    }

    public void checkWinner(final Coordinate coordinate) {
        Map<Coordinate, Symbol> symbolMap = boardStatus.getSymbolsAtCoordinates();
        if(symbolMap.size() >= 3){   //todo magic number, ktory ma byc iloscia do zwycieztwa
            if(isInVerticalWinningSequence(coordinate)){
                isWinner = true;
            }
        }
    }

    private boolean isInVerticalWinningSequence(Coordinate coordinate) {
        Map<Coordinate, Symbol> row = boardStatus.getElementsInRow(coordinate.getX());
        System.out.println("W tym wierszu: " + row);

        int leftMostSymbolOfIndex = getLeftMostIndexOfSymbolInSequence(row, coordinate);
        int rightMostSymbolOfIndex = getRightMostIndexOfSymbolInSequence(row, coordinate);

        System.out.println("Left most: " + leftMostSymbolOfIndex);
        System.out.println("Right most: " + rightMostSymbolOfIndex);

        if(rightMostSymbolOfIndex - leftMostSymbolOfIndex >= 2){
            return true;
        }


        return false;
    }

    private int getLeftMostIndexOfSymbolInSequence(Map<Coordinate, Symbol> row, Coordinate actualCoordinate) {
        Symbol actualSymbol = row.get(actualCoordinate);
        Optional<Symbol> symbol = Optional.ofNullable(row.get(new Coordinate(actualCoordinate.getX(), actualCoordinate.getY())));

        int min = Integer.MAX_VALUE;
        int i = actualCoordinate.getY();

        while (symbol.isPresent()){
            if(symbol.get().equals(actualSymbol) && i < min){
                min = i;
            }
            i--;
            symbol = Optional.ofNullable(row.get(new Coordinate(actualCoordinate.getX(), i)));
        }

        return min;
    }

    private int getRightMostIndexOfSymbolInSequence(Map<Coordinate, Symbol> row, Coordinate actualCoordinate){
        Symbol actualSymbol = row.get(actualCoordinate);
        Optional<Symbol> symbol = Optional.ofNullable(row.get(new Coordinate(actualCoordinate.getX(), actualCoordinate.getY())));

        int max = Integer.MIN_VALUE;
        int i = actualCoordinate.getY();

        while (symbol.isPresent()){
            if(symbol.get().equals(actualSymbol) && i > max){
                max = i;
            }
            i++;
            symbol = Optional.ofNullable(row.get(new Coordinate(actualCoordinate.getX(), i)));
        }

        return max;
    }
}
