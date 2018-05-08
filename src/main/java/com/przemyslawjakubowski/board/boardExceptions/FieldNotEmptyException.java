package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.textOutput.ReplacePattern;

public class FieldNotEmptyException extends Throwable {
    String exceptionMessage;

    public FieldNotEmptyException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_FIELD_NOT_EMPTY, new ReplacePattern("%field%", exceptionMessage));
        outputConsumer.accept("=============================================\n");
    }
}
