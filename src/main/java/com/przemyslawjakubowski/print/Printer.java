package com.przemyslawjakubowski.print;

import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.board.Coordinate;
import com.przemyslawjakubowski.player.Symbol;

import java.util.Optional;
import java.util.function.Consumer;

public class Printer {

    public static void printBoard(BoardStatus boardStatus, Consumer<String> output) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < boardStatus.getRows(); i++){
            prepareColumnString(boardStatus, stringBuilder, i);
            stringBuilder.append("\n\n");
        }
        output.accept(stringBuilder.toString());
    }

    private static void prepareColumnString(BoardStatus boardStatus, StringBuilder stringBuilder, int i) {
        for(int j = 0; j < boardStatus.getColumns(); j++){
            Coordinate currentCoordinate = new Coordinate(i, j);
            Optional<Symbol> symbolAtCoordinate = boardStatus.getSymbolAtCoordinate(currentCoordinate);
            if(symbolAtCoordinate.isPresent()){
                stringBuilder.append(symbolAtCoordinate.get());
            }
            else{
                stringBuilder.append(i + " " + j);
            }
            stringBuilder.append("\t\t");
        }
    }
}
