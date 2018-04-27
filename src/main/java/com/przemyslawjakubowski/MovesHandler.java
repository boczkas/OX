package com.przemyslawjakubowski;

import com.przemyslawjakubowski.boardExceptions.BoardIndexOutOfBoundsException;
import com.przemyslawjakubowski.boardExceptions.FieldNotEmptyException;
import com.przemyslawjakubowski.boardExceptions.IncorrectCoordinateException;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovesHandler {
    final int rows;
    final int columns;
    final Pattern inputPattern;
    final BoardStatus boardStatus;

    public MovesHandler(BoardStatus boardStatus) {
        this.rows = boardStatus.getRows();
        this.columns = boardStatus.getColumns();
        this.inputPattern = Pattern.compile("(\\d+)\\s(\\d+)\\s*");
        this.boardStatus = boardStatus;
    }

    public void handleMoves(Supplier<String> userInput, Consumer<String> output, Player player) {
        output.accept("Ruch wykonuje gracz: " + player.getName() + " (" + player.getSymbol() + ")");

        handleCoordinateInput(userInput, output, player);
    }

    private void handleCoordinateInput(Supplier<String> userInput, Consumer<String> output, Player player) {
        String userInputString = userInput.get().trim();
        Matcher matcher = inputPattern.matcher(userInputString.replaceAll("\\s+", " "));

        if(matcher.matches()) {
            Coordinate coordinate;
            Integer x = Integer.parseInt(matcher.group(1));
            Integer y = Integer.parseInt(matcher.group(2));

            if(areSpecifiedCoordinatesCorrect(x, y)){
                coordinate = new Coordinate(x, y);

                if (boardStatus.checkIfFieldIsEmpty(coordinate)) {
                    boardStatus.addSymbolAtPosition(player.getSymbol(), coordinate);
                } else {
                    try {
                        throw new FieldNotEmptyException(coordinate.toString());
                    } catch (FieldNotEmptyException e) {
                        output.accept(e.toString());
                        handleMoves(userInput, output, player);
                    }
                }
            }
            else{
                try{
                    throw new BoardIndexOutOfBoundsException();
                } catch (BoardIndexOutOfBoundsException e){
                    output.accept(e.toString());
                    handleMoves(userInput, output, player);
                }
            }
        }
        else{
            try {
                throw new IncorrectCoordinateException(userInputString);
            } catch (IncorrectCoordinateException e){
                output.accept(e.toString());
                handleMoves(userInput, output, player);
            }

        }
    }

    private boolean areSpecifiedCoordinatesCorrect(Integer x, Integer y) {
        boolean result = true;

        if(x < 0 || y < 0){
            result = false;
        }

        if(x >= rows || y >= columns){
            result = false;
        }

        return result;
    }
}
