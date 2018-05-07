package com.przemyslawjakubowski;

import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.board.Coordinate;
import com.przemyslawjakubowski.board.boardExceptions.BoardIndexOutOfBoundsException;
import com.przemyslawjakubowski.board.boardExceptions.FieldNotEmptyException;
import com.przemyslawjakubowski.board.boardExceptions.IncorrectCoordinateException;
import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.player.Player;

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

    public EndRequest handleMoves(Supplier<String> userInput, OutputConsumer output, Player player, Judge judge) {
        output.accept("Ruch wykonuje gracz: " + player.getName() + " (" + player.getSymbol() + ")");
        return handleCoordinateInput(userInput, output, player, judge);
    }

    private EndRequest handleCoordinateInput(Supplier<String> userInput, OutputConsumer output, Player player, Judge judge) {
        String userInputString = userInput.get().trim();
        if(userInputString.contains("end") || userInputString.contains("koniec")){
            return EndRequest.YES;
        }

        Matcher matcher = inputPattern.matcher(userInputString.replaceAll("\\s+", " "));

        if(matcher.matches()) {
            Coordinate coordinate;
            Integer x = Integer.parseInt(matcher.group(1));
            Integer y = Integer.parseInt(matcher.group(2));

            if(areSpecifiedCoordinatesCorrect(x, y)){
                coordinate = new Coordinate(x, y);

                if (boardStatus.checkIfFieldIsEmpty(coordinate)) {
                    boardStatus.addSymbolAtCoordinate(player.getSymbol(), coordinate);
                    judge.checkWin(coordinate);
                } else {
                    try {
                        throw new FieldNotEmptyException(coordinate.toString());
                    } catch (FieldNotEmptyException e) {
                        output.accept(e.toString());
                        handleMoves(userInput, output, player, judge);
                    }
                }
            }
            else{
                try{
                    throw new BoardIndexOutOfBoundsException();
                } catch (BoardIndexOutOfBoundsException e){
                    e.printExceptionMessage(output);
                    handleMoves(userInput, output, player, judge);
                }
            }
        }
        else{
            try {
                throw new IncorrectCoordinateException(userInputString);
            } catch (IncorrectCoordinateException e){
                output.accept(e.toString());
                handleMoves(userInput, output, player, judge);
            }

        }
        return EndRequest.NO;
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
