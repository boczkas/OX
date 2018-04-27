package com.przemyslawjakubowski;

public class IncorrectCoordinateException extends Throwable {
    String exceptionMessage;

    IncorrectCoordinateException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString(){
        return "==================================================\n" +
                exceptionMessage + " nie jest prawidłową wartością\n" +
                "=================================================\n";
    }
}
