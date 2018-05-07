package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;
import com.przemyslawjakubowski.output.ReplacePattern;

public class FieldNotEmptyException extends Throwable {
    String exceptionMessage;

    public FieldNotEmptyException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public void printExceptionMessage(OutputConsumer outputConsumer, String exceptionMessage){
        outputConsumer.accept(OutputOption.EXCEPTION_FIELD_NOT_EMPTY, new ReplacePattern("%field%", exceptionMessage));
    }

    @Override
    public String toString(){
        return "=============================================\n" +
                "Pole " + exceptionMessage + " nie jest puste\n" +
                "Wybierz puste pole\n" +
                "============================================\n";
    }
}
