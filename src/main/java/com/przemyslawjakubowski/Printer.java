package com.przemyslawjakubowski;

import java.util.Optional;

public class Printer {

    public static void print(BoardStatus boardStatus) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < boardStatus.getRows(); i++){
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
            stringBuilder.append("\n\n");
        }
        System.out.println(stringBuilder);
    }
}
