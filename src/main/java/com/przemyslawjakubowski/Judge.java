package com.przemyslawjakubowski;

import com.przemyslawjakubowski.player.Symbol;

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
//            if(isInHorizontalWinningSequence(coordinate)){
//                isWinner = true;
//            }
        }
    }

//    private boolean isInHorizontalWinningSequence(Coordinate coordinate) {
//        Map<Coordinate, Symbol> column = boardStatus.getElementsInColumn(coordinate.getY());
//        System.out.println("W tej kolumnie: " + column);
//
//        int indexOfTopMostSymbolInSequence = getTopMostIndexOfSymbolInSequence(column, coordinate);
//    }
//
//    private int getTopMostIndexOfSymbolInSequence(Map<Coordinate, Symbol> column, Coordinate coordinate) {
//        return 0;
//    }

    private boolean isInVerticalWinningSequence(Coordinate coordinate) {
        Map<Coordinate, Symbol> row = boardStatus.getElementsInRow(coordinate.getX());
        System.out.println("W tym wierszu: " + row);

        int indexOfLeftMostSymbolInSequence = getLeftMostIndexOfSymbolInSequence(row, coordinate);
        int indexOfRightMostSymbolInSequence = getRightMostIndexOfSymbolInSequence(row, coordinate);

        System.out.println("Left most: " + indexOfLeftMostSymbolInSequence);
        System.out.println("Right most: " + indexOfRightMostSymbolInSequence);

        if(indexOfRightMostSymbolInSequence - indexOfLeftMostSymbolInSequence >= 2){
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
