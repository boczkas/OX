package com.przemyslawjakubowski.board.boardExceptions;

public class IncorrectCoordinateException extends Throwable {
    String exceptionMessage;

    public IncorrectCoordinateException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString(){
        return "==================================================\n" +
                exceptionMessage + " nie jest prawidłową wartością\n" +
                "=================================================\n";
    }
}
