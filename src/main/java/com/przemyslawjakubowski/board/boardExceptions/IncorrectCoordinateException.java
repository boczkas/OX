package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.textOutput.ReplacePattern;

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
