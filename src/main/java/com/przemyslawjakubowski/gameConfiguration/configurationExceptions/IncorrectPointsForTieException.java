package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

import com.przemyslawjakubowski.player.Point;

public class IncorrectPointsForTieException extends Throwable {

    Point pointsForTie;
    Point pointsFroWon;

    public IncorrectPointsForTieException(Point pointsForTie, Point pointsForWon) {
        this.pointsForTie = pointsForTie;
        this.pointsFroWon = pointsForWon;
    }

    @Override
    public String toString(){
        return "==================================================\n" +
                "Wartość " + pointsForTie.toString() + " nie może być ustawiona jako  \n" +
                "wartość punktów otrzymywanych za remis, musi być \n" +
                "ona większa od puntków za zwycięztwo: " + pointsFroWon.toString() + "\n" +
                "=================================================\n";
    }
}
