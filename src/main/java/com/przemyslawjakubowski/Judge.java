package com.przemyslawjakubowski;

import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.board.Coordinate;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWin;
import com.przemyslawjakubowski.player.Symbol;

import java.util.Map;
import java.util.Optional;

public class Judge {
    boolean isWinner;
    BoardStatus boardStatus;
    SymbolsToWin symbolsToWin;
    int symbolsCounter;

    public Judge(BoardStatus boardStatus, SymbolsToWin symbolsToWin) {
        this.boardStatus = boardStatus;
        this.isWinner = false;
        this.symbolsToWin = symbolsToWin;
        this.symbolsCounter = 0;
    }

    public boolean isWinnerPresent() {
        return isWinner;
    }

    public void checkWin(final Coordinate coordinate) {
        Map<Coordinate, Symbol> symbolMap = boardStatus.getSymbolsAtCoordinates();
        int amountOfSymbolsToWin = symbolsToWin.getAmountOfSymbolsToWin();

        if(symbolMap.size() >= amountOfSymbolsToWin &&
                (isInVerticalWinningSequence(coordinate, amountOfSymbolsToWin) ||
                isInHorizontalWinningSequence(coordinate, amountOfSymbolsToWin) ||
                isInLeftRightDiagonalWinningSequence(coordinate, amountOfSymbolsToWin) ||
                isInRightLefDiagonalWinningSequence(coordinate, amountOfSymbolsToWin))){

            isWinner = true;
        }

        symbolsCounter++;
    }

    public boolean isTie(){

        if(symbolsCounter == boardStatus.getPossibleAmountOfSymbols()){
            return true;
        }
        return false;
    }

    private boolean isInHorizontalWinningSequence(Coordinate coordinate, int amountOfSymbolsToWin) {
        Map<Coordinate, Symbol> column = boardStatus.getElementsInColumn(coordinate.getY());

        int indexOfTopMostSymbolInSequence = getTopMostIndexOfSymbolInSequence(column, coordinate);
        int indexOfBottomMostSymbolInSequence = getBottomMostIndexOfSymbolInSequence(column, coordinate);

        if(indexOfTopMostSymbolInSequence - indexOfBottomMostSymbolInSequence >= amountOfSymbolsToWin - 1){
            return true;
        }
        return false;
    }

    private boolean isInVerticalWinningSequence(Coordinate coordinate, int amountOfSymbolsToWin) {
        Map<Coordinate, Symbol> row = boardStatus.getElementsInRow(coordinate.getX());

        int indexOfLeftMostSymbolInSequence = getLeftMostIndexOfSymbolInSequence(row, coordinate);
        int indexOfRightMostSymbolInSequence = getRightMostIndexOfSymbolInSequence(row, coordinate);

        if(indexOfRightMostSymbolInSequence - indexOfLeftMostSymbolInSequence >= amountOfSymbolsToWin - 1){
            return true;
        }
        return false;
    }

    private boolean isInLeftRightDiagonalWinningSequence(Coordinate coordinate, int amountOfSymbolsToWin){
        Map<Coordinate, Symbol> diagonal = boardStatus.getElementsInLeftDiagonal(coordinate);

        Coordinate coordinateOfLeftTopSymbolInSequence = getCoordinateOfLeftTopSymbolInSequence(diagonal, coordinate);
        Coordinate coordinateOfRightBottomSymbolInSequence = getCoordinateOfRightBottomSymbolInSequence(diagonal, coordinate);

        if(coordinateOfRightBottomSymbolInSequence.getX() - coordinateOfLeftTopSymbolInSequence.getX() >= amountOfSymbolsToWin - 1){
            return true;
        }
        return false;
    }

    private boolean isInRightLefDiagonalWinningSequence(Coordinate coordinate, int amountOfSymbolsToWin) {
        Map<Coordinate, Symbol> diagonal = boardStatus.getElementsInRightDiagonal(coordinate);
        Coordinate coordinateOfRightTopSymbolInSequence = getCoordinateOfRightTopSymbolInSequence(diagonal, coordinate);
        Coordinate coordinateOfLeftBottomSymbolInSequence = getCoordinateOfLeftBottomSymbolInSequence(diagonal, coordinate);

        if(coordinateOfLeftBottomSymbolInSequence.getX() - coordinateOfRightTopSymbolInSequence.getX() >= amountOfSymbolsToWin - 1){
            return true;
        }
        return false;
    }

    private Coordinate getCoordinateOfLeftBottomSymbolInSequence(Map<Coordinate,Symbol> diagonal, Coordinate actualCoordinate) {
        Symbol actualSymbol = diagonal.get(actualCoordinate);

        Coordinate bottomLeft = actualCoordinate;
        Coordinate checking = new Coordinate(actualCoordinate.getX() + 1, actualCoordinate.getY() - 1);

        while (diagonal.containsKey(checking)){
            if(diagonal.get(checking).equals(actualSymbol)){
                bottomLeft = checking;
            }
            else
                break;
            checking = new Coordinate(checking.getX() + 1, checking.getY() - 1);
        }
        return bottomLeft;
    }

    private Coordinate getCoordinateOfRightTopSymbolInSequence(Map<Coordinate,Symbol> diagonal, Coordinate actualCoordinate) {
        Symbol actualSymbol = diagonal.get(actualCoordinate);

        Coordinate topRight = actualCoordinate;
        Coordinate checking = new Coordinate(actualCoordinate.getX() - 1, actualCoordinate.getY() + 1);

        while (diagonal.containsKey(checking)){
            if(diagonal.get(checking).equals(actualSymbol)){
                topRight = checking;
            }
            else
                break;
            checking = new Coordinate(checking.getX() - 1, checking.getY() + 1);
        }
        return topRight;
    }

    private Coordinate getCoordinateOfRightBottomSymbolInSequence(Map<Coordinate,Symbol> diagonal, Coordinate actualCoordinate) {
        Symbol actualSymbol = diagonal.get(actualCoordinate);
        Coordinate bottomRight = actualCoordinate;
        Coordinate checking = new Coordinate(actualCoordinate.getX() + 1, actualCoordinate.getY() + 1);

        while (diagonal.containsKey(checking)){
            if(diagonal.get(checking).equals(actualSymbol)){
                bottomRight = checking;
            }
            else
                break;
            checking = new Coordinate(checking.getX() + 1, checking.getY() + 1);
        }
        return bottomRight;
    }

    private Coordinate getCoordinateOfLeftTopSymbolInSequence(Map<Coordinate,Symbol> diagonal, Coordinate actualCoordinate) {
        Symbol actualSymbol = diagonal.get(actualCoordinate);

        Coordinate topLeft = actualCoordinate;
        Coordinate checking = new Coordinate(actualCoordinate.getX() - 1, actualCoordinate.getY() - 1);

        while (diagonal.containsKey(checking)){
            if(diagonal.get(checking).equals(actualSymbol)){
                topLeft = checking;
            }
            else
                break;
            checking = new Coordinate(checking.getX() - 1, checking.getY() - 1);
        }
        return topLeft;
    }


    private int getLeftMostIndexOfSymbolInSequence(Map<Coordinate, Symbol> row, Coordinate actualCoordinate) {
        Symbol actualSymbol = row.get(actualCoordinate);
        Optional<Symbol> symbol = Optional.ofNullable(actualSymbol);

        int min = Integer.MAX_VALUE;
        int i = actualCoordinate.getY();

        while (symbol.isPresent()){
            if(symbol.get().equals(actualSymbol) && i < min){
                min = i;
            }
            else
                break;
            i--;
            symbol = Optional.ofNullable(row.get(new Coordinate(actualCoordinate.getX(), i)));
        }

        return min;
    }

    private int getRightMostIndexOfSymbolInSequence(Map<Coordinate, Symbol> row, Coordinate actualCoordinate){
        Symbol actualSymbol = row.get(actualCoordinate);
        Optional<Symbol> symbol = Optional.ofNullable(actualSymbol);

        int max = Integer.MIN_VALUE;
        int i = actualCoordinate.getY();

        while (symbol.isPresent()){
            if(symbol.get().equals(actualSymbol) && i > max){
                max = i;
            }
            else
                break;
            i++;
            symbol = Optional.ofNullable(row.get(new Coordinate(actualCoordinate.getX(), i)));
        }

        return max;
    }

    private int getTopMostIndexOfSymbolInSequence(Map<Coordinate, Symbol> column, Coordinate actualCoordinate) {
        Symbol actualSymbol = column.get(actualCoordinate);
        Optional<Symbol> symbol = Optional.ofNullable(actualSymbol);

        int max = Integer.MIN_VALUE;
        int i = actualCoordinate.getX();

        while (symbol.isPresent()){
            if(symbol.get().equals(actualSymbol) && i > max){
                max = i;
            }
            else
                break;
            i++;
            symbol = Optional.ofNullable(column.get(new Coordinate(i, actualCoordinate.getY())));
        }

        return max;
    }

    private int getBottomMostIndexOfSymbolInSequence(Map<Coordinate, Symbol> column, Coordinate actualCoordinate){
        Symbol actualSymbol = column.get(actualCoordinate);
        Optional<Symbol> symbol = Optional.ofNullable(actualSymbol);

        int min = Integer.MAX_VALUE;
        int i = actualCoordinate.getX();

        while(symbol.isPresent()){
            if(symbol.get().equals(actualSymbol) && i < min){
                min = i;
            }
            else
                break;
            i--;
            symbol = Optional.ofNullable(column.get(new Coordinate(i, actualCoordinate.getY())));
        }

        return min;
    }
}
