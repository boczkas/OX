package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.textOutput.OutputOption;

public class BoardIndexOutOfBoundsException extends Throwable {

    public BoardIndexOutOfBoundsException(){
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_BOARD_INDEX_OUT_OF_BOUND);
        outputConsumer.accept("=============================================\n");
    }
}
