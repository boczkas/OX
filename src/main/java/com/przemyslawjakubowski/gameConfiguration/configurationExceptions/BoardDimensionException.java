package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

public class BoardDimensionException extends Throwable {
    @Override
    public String toString(){
        return "==================================================\n" +
                "Wartość wymiaru tablicy musi być większa od 0    \n" +
                "=================================================\n";
    }
}
