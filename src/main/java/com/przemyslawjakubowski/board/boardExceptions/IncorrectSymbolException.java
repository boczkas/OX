package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.textOutput.ReplacePattern;

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
