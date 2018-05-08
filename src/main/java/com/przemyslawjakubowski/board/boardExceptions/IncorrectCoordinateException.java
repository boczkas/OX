package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;
import com.przemyslawjakubowski.output.ReplacePattern;

public class IncorrectCoordinateException extends Throwable {
    String exceptionMessage;

    public IncorrectCoordinateException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_INCORRECT_COORDINATE, new ReplacePattern("%text%", exceptionMessage));
        outputConsumer.accept("=============================================\n");
    }
}
