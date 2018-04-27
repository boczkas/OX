package com.przemyslawjakubowski.boardExceptions;

public class BoardIndexOutOfBoundsException extends Throwable {

    public BoardIndexOutOfBoundsException( ){
    }

    @Override
    public String toString(){
        return "=============================================\n" +
                " Przekroczono rozmiar tablicy\n" +
                "============================================\n";
    }
}
