package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;

public class BoardDimensionException extends Throwable {

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_BOARD_DIMENSION);
        outputConsumer.accept("=============================================\n");
    }
}
