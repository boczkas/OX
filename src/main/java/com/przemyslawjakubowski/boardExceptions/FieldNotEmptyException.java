package com.przemyslawjakubowski;

public class FieldNotEmptyException extends Throwable {
    String exceptionMessage;

    FieldNotEmptyException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString(){
        return "=============================================\n" +
                "Pole " + exceptionMessage + " nie jest puste\n" +
                "Wybierz puste pole\n" +
                "============================================\n";
    }
}
