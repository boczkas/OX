package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;

public class BoardDimensionException extends Throwable {

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_BOARD_DIMENSION);
        outputConsumer.accept("=============================================\n");
    }
}
