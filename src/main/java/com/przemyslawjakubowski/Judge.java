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
            if(isInHorizontalWinningSequence(coordinate)){
                isWinner = true;
            }
            if(isInLeftRightWinningSequence(coordinate)){
                isWinner = true;
            }
            if(isInRightLeftWinningSequence(coordinate)){
                isWinner = true;
            }
        }
    }

    private boolean isInHorizontalWinningSequence(Coordinate coordinate) {
        Map<Coordinate, Symbol> column = boardStatus.getElementsInColumn(coordinate.getY());
        System.out.println("W tej kolumnie: " + column);

        int indexOfTopMostSymbolInSequence = getTopMostIndexOfSymbolInSequence(column, coordinate);
        int indexOfBottomMostSymbolInSequence = getBottomMostIndexOfSymbolInSequence(column, coordinate);
        System.out.println("top Most: " + indexOfTopMostSymbolInSequence);
        System.out.println("bootom Most: " + indexOfBottomMostSymbolInSequence);

        if(indexOfTopMostSymbolInSequence - indexOfBottomMostSymbolInSequence >= 2){
            return true; // todo przeczytac z konfiguracji wartosc
        }
        return false;
    }

    private boolean isInVerticalWinningSequence(Coordinate coordinate) {
        Map<Coordinate, Symbol> row = boardStatus.getElementsInRow(coordinate.getX());
        System.out.println("W tym wierszu: " + row);

        int indexOfLeftMostSymbolInSequence = getLeftMostIndexOfSymbolInSequence(row, coordinate);
        int indexOfRightMostSymbolInSequence = getRightMostIndexOfSymbolInSequence(row, coordinate);

        System.out.println("Left most: " + indexOfLeftMostSymbolInSequence);
        System.out.println("Right most: " + indexOfRightMostSymbolInSequence);

        if(indexOfRightMostSymbolInSequence - indexOfLeftMostSymbolInSequence >= 2){ // todo przeczytac z konfiguracji wartosc
            return true;
        }
        return false;
    }

    private boolean isInLeftRightWinningSequence(Coordinate coordinate){
        Map<Coordinate, Symbol> diagonal = boardStatus.getElementsInLeftDiagonal(coordinate);
        System.out.println("W tej przekątnej: " + diagonal);
        Coordinate coordinateOfLeftTopSymbolInSequence = getCoordinateOfLeftTopSymbolInSequence(diagonal, coordinate);
        Coordinate coordinateOfRightBottomSymbolInSequence = getCoordinateOfRightBottomSymbolInSequence(diagonal, coordinate);
        System.out.println("Left top: " + coordinateOfLeftTopSymbolInSequence);
        System.out.println("Bottom right: " + coordinateOfRightBottomSymbolInSequence);

        if(coordinateOfRightBottomSymbolInSequence.getX() - coordinateOfLeftTopSymbolInSequence.getX() >= 2){
            return true;
        }
        return false;
    }

    private boolean isInRightLeftWinningSequence(Coordinate coordinate) {
        Map<Coordinate, Symbol> diagonal = boardStatus.getElementsInRightDiagonal(coordinate);
        System.out.println("W tej przekątnej: " + diagonal);
        Coordinate coordinateOfRightTopSymbolInSequence = getCoordinateOfRightTopSymbolInSequence(diagonal, coordinate);
        Coordinate coordinateOfLeftBottomSymbolInSequence = getCoordinateOfLeftBottomSymbolInSequence(diagonal, coordinate);

        System.out.println("Right top: " + coordinateOfRightTopSymbolInSequence);
        System.out.println("Bottom left: " + coordinateOfLeftBottomSymbolInSequence);

        if(coordinateOfLeftBottomSymbolInSequence.getX() - coordinateOfRightTopSymbolInSequence.getX() >=2){
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
