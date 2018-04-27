package com.przemyslawjakubowski;

public enum Symbol {
    X(Color.ANSI_CYAN + "X" + Color.ANSI_DEFAULT),
    O(Color.ANSI_YELLOW + "O" + Color.ANSI_DEFAULT);

    private String stringRepresentation;

    Symbol(String stringRepresentation){
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public String toString(){
        return stringRepresentation;
    }
}
