package com.przemyslawjakubowski;

public class BoardIndexOutOfBoundsException extends Throwable {

    BoardIndexOutOfBoundsException( ){
    }

    @Override
    public String toString(){
        return "=============================================\n" +
                " Przekroczono rozmiar tablicy\n" +
                "============================================\n";
    }
}
