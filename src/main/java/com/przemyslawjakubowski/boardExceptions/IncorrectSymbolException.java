package com.przemyslawjakubowski;

public class IncorrectSymbolException extends Throwable{

    String exceptionMessage;

    IncorrectSymbolException(String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString(){
        return "Niepoprawny symbol.\n" +
                "Powinenes wyspecyfikowac jeden z X/O.\n" +
                "Wpisano: " + exceptionMessage;
    }

}
