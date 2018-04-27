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
        this.inputPattern = Pattern.compile("(\\d+)\\s(\\d+)\\s*");
        this.boardStatus = boardStatus;
    }

    public void handleMoves(Supplier<String> userInput, Consumer<String> output, Player player) {
        String sizeInformation = "Przeciez mowiles ze chcesz grac na planszy 3x3, to co Ty mi tu piszesz?!\n";
        String formatInformation = "Ruch dostarczaj w postaci <numer_wiersza>[Spacja]<numer_kolumny> przykładowo: 1 2\n";
        output.accept(formatInformation);
        output.accept("Ruch wykonuje gracz: " + player.getName() + " (" + player.getSymbol() + ")");

        String userInputString = userInput.get().trim();

        userInputString = userInputString.replaceAll("\\s+", " ");
        String outputInformation = "";
        Matcher matcher = inputPattern.matcher(userInputString);

        if(matcher.matches()) {
            Coordinate coordinate;
            Integer x = Integer.parseInt(matcher.group(1));
            Integer y = Integer.parseInt(matcher.group(2));

            if(areSpecifiedCoordinatesCorrect(x, y)){
                coordinate = new Coordinate(x, y);

                if (boardStatus.checkIfFieldIsEmpty(coordinate)) {
                    boardStatus.addSymbolAtPosition(player.getSymbol(), coordinate);
                    outputInformation = "Ładnie powiedziane";
                } else {
                    outputInformation = "O Ty oszukisto! Chciałeś oszukać i postawić na zajętym polu!\n" +
                            "Nie ma takiego grania! Tracisz ruch!";
                }
            }
        }
        else{
            outputInformation = sizeInformation;
        }

        output.accept(outputInformation);
    }

    private boolean areSpecifiedCoordinatesCorrect(Integer x, Integer y) {
        boolean result = true;

        if(x < 0 || y < 0){
            result = false;
        }

        if(x > rows || y > columns){
            result = false;
        }

        return result;
    }

    public void print() {
        boardStatus.print();
    }
}
