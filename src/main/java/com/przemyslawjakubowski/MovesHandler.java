package com.przemyslawjakubowski;

import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.board.Coordinate;
import com.przemyslawjakubowski.board.boardExceptions.BoardIndexOutOfBoundsException;
import com.przemyslawjakubowski.board.boardExceptions.FieldNotEmptyException;
import com.przemyslawjakubowski.board.boardExceptions.IncorrectCoordinateException;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.textOutput.ReplacePattern;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.userInput.UserInputProvider;

import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovesHandler {
    final int rows;
    final int columns;
    final Pattern inputPattern;
    final BoardStatus boardStatus;
    boolean isMoveCorrect;
    EndRequest endRequest;

    public MovesHandler(BoardStatus boardStatus) {
        this.rows = boardStatus.getRows();
        this.columns = boardStatus.getColumns();
        this.inputPattern = Pattern.compile("(\\d+)\\s(\\d+)\\s*");
        this.boardStatus = boardStatus;
        this.isMoveCorrect = true;
        this.endRequest = EndRequest.NO;
    }

    public EndRequest handleMoves(UserInputProvider userInput, OutputConsumer output, Player player, Judge judge) {
        output.accept(OutputOption.CURRENTLY_PLAYING, new ReplacePattern("%playerName%", player.getName()),
                                                      new ReplacePattern("%playerSymbol%", player.getSymbol().toString()));

        handleCoordinateInput(userInput, output, player, judge);
        return endRequest;
    }

    private void handleCoordinateInput(UserInputProvider userInput, OutputConsumer output, Player player, Judge judge) {
        String userInputString = userInput.get().trim();
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
                    isMoveCorrect = true;
                } else {
                    try {
                        throw new FieldNotEmptyException(coordinate.toString());
                    } catch (FieldNotEmptyException e) {
                        e.printExceptionMessage(output);
                        isMoveCorrect = false;
                    }
                }
            }
            else{
                try{
                    throw new BoardIndexOutOfBoundsException();
                } catch (BoardIndexOutOfBoundsException e){
                    e.printExceptionMessage(output);
                    isMoveCorrect = false;
                }
            }
        }
        else{
            try {
                throw new IncorrectCoordinateException(userInputString);
            } catch (IncorrectCoordinateException e){
                e.printExceptionMessage(output);
                isMoveCorrect = false;
            }

        }

        if(!isMoveCorrect){
            if(userInputString.contains("end") || userInputString.contains("koniec")){
                endRequest = EndRequest.YES;
            }
            else{
                handleCoordinateInput(userInput, output, player, judge);
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
