package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;
import com.przemyslawjakubowski.output.ReplacePattern;

public class IncorrectSymbolException extends Throwable{

    String exceptionMessage;

    public IncorrectSymbolException(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_INCORRECT_SYMBOL, new ReplacePattern("%symbol%", exceptionMessage));
        outputConsumer.accept("=============================================\n");
    }
}
