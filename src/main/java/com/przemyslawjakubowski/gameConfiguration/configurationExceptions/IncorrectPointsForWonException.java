package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

public class IncorrectPointsForWonException extends Throwable {
    String exceptionMessage;

    public IncorrectPointsForWonException( String exceptionMessage){
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString(){
        return "==================================================\n" +
                exceptionMessage + " nie może być ustawiona jako  \n" +
                "wartość punktów otrzymywanych za zwycięztwo,     \n" +
                "musi być ona > 0\n" +
                "=================================================\n";
    }
}
