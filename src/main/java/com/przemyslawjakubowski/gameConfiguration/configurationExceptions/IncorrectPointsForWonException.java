package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;
import com.przemyslawjakubowski.textOutput.ReplacePattern;

public class IncorrectPointsForWonException extends Throwable {
    String exceptionMessage;

    public IncorrectPointsForWonException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_INCORRECT_POINTS_FOR_WON, new ReplacePattern("%pointsForWin%", exceptionMessage));
        outputConsumer.accept("=============================================\n");
    }
}
