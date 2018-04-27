package com.przemyslawjakubowski.print;

import com.przemyslawjakubowski.BoardStatus;
import com.przemyslawjakubowski.Coordinate;
import com.przemyslawjakubowski.Symbol;

import java.util.Optional;

public class Printer {

    public static void print(BoardStatus boardStatus) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < boardStatus.getRows(); i++){
            prepareColumnString(boardStatus, stringBuilder, i);
            stringBuilder.append("\n\n");
        }
        System.out.println(stringBuilder);
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
