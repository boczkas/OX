package com.przemyslawjakubowski;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
    int rows;
    int columns;
    Pattern inputPattern;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.inputPattern = Pattern.compile("\\d+\\s\\d+");
    }

    public void handleMoves(Supplier<String> userInput, Consumer<String> output) {
        String sizeInformation = "Przeciez mowiles ze chcesz grac na planszy 3x3, to co Ty mi tu piszesz?!\n";
        String formatInformation = "Ruch dostarczaj w postaci <numer_wiersza>[Spacja]<numer_kolumny> przykładowo: 1 2\n";
        output.accept(formatInformation);

        String userInputString = userInput.get();
        String outputInformation = "";
        Matcher m = inputPattern.matcher(userInputString);

        if(!m.matches()){
            outputInformation += formatInformation;
            if(!areSpecifiedCoordinatesCorrect(userInputString)){
                outputInformation += sizeInformation;
            }
        }
        else{
            outputInformation = "Ładnie powiedziane";
        }

        output.accept(outputInformation);
    }

    private boolean areSpecifiedCoordinatesCorrect(String userInputString) {
        String[] coordinates = userInputString.split("\\s");
        boolean result = true;

        String lettersRegex = "[a-zA-Z]+";
        if(coordinates.length != 2 || coordinates[0].matches(lettersRegex) || coordinates[1].matches(lettersRegex)){
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
}
