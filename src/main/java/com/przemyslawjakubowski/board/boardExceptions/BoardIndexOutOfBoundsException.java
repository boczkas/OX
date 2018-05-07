package com.przemyslawjakubowski.board.boardExceptions;

import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;

public class BoardIndexOutOfBoundsException extends Throwable {

    public BoardIndexOutOfBoundsException(){
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_BOARD_INDEX_OUT_OF_BOUND);
        outputConsumer.accept("=============================================\n");
    }
}
