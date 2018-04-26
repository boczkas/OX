package com.przemyslawjakubowski;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
    final int rows;
    final int columns;
    final Pattern inputPattern;
    final BoardStatus boardStatus;

    public Board(BoardStatus boardStatus) {
        this.rows = boardStatus.getRows();
        this.columns = boardStatus.getColumns();
        this.inputPattern = Pattern.compile("\\d+\\s\\d+\\s*");
        this.boardStatus = boardStatus;
    }

    public void handleMoves(Supplier<String> userInput, Consumer<String> output) {
        String sizeInformation = "Przeciez mowiles ze chcesz grac na planszy 3x3, to co Ty mi tu piszesz?!\n";
        String formatInformation = "Ruch dostarczaj w postaci <numer_wiersza>[Spacja]<numer_kolumny> przykładowo: 1 2\n";
        output.accept(formatInformation);

        String userInputString = userInput.get();
        if(userInputString.startsWith(" ")){
            userInputString = userInputString.replaceFirst("\\s+", "");
        }

        userInputString = userInputString.replaceAll("\\s+", " ");
        String outputInformation = "";
        Matcher m = inputPattern.matcher(userInputString);

        if(!m.matches()){
            outputInformation += formatInformation;
            if(!areSpecifiedCoordinatesCorrect(userInputString)){
                outputInformation += sizeInformation;
            }
        }
        else{
            String[] coordinateStringArray = userInputString.split("\\s");
            Coordinate coordinate = new Coordinate(Integer.parseInt(coordinateStringArray[0]),
                                                    Integer.parseInt(coordinateStringArray[1]));
            if(boardStatus.checkIfFieldIsEmpty(coordinate)){
                boardStatus.addSymbolAtPosition(Symbol.O, coordinate);
                outputInformation = "Ładnie powiedziane";
            }
            else{
                outputInformation = "O Ty oszukisto! Chciałeś oszukać i postawić na zajętym polu!\n" +
                        "Nie ma takiego grania! Tracisz ruch!";
            }

        }

        output.accept(outputInformation);
    }

    private boolean areSpecifiedCoordinatesCorrect(String userInputString) {
        String[] coordinates = userInputString.split("\\s");
        boolean result = true;

        String lettersRegex = "[a-zA-Z]+";
        if(coordinates.length < 2 || coordinates[0].matches(lettersRegex) || coordinates[1].matches(lettersRegex)){
            result = false;
        }

        else{
            int rowNumber = Integer.parseInt(coordinates[0]);
            int columnNumber = Integer.parseInt(coordinates[1]);

            if(rowNumber < 0 || columnNumber < 0){
                result = false;
            }

            if(rowNumber > rows || columnNumber > columns){
                result = false;
            }
        }

        return result;
    }

    public void print() {
        boardStatus.print();
    }
}
