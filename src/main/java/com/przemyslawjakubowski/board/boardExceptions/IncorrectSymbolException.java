package com.przemyslawjakubowski.board.boardExceptions;

public class IncorrectSymbolException extends Throwable{

    String exceptionMessage;

    public IncorrectSymbolException(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString(){
        return "==========================================\n" +
                "Niepoprawny symbol.\n" +
                "Powinenes wyspecyfikowac jeden z X/O.\n" +
                "Wpisano: " + exceptionMessage +
                "==========================================\n";
    }

}
