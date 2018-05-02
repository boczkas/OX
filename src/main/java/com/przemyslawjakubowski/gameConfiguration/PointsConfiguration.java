package com.przemyslawjakubowski.gameConfiguration;

import com.przemyslawjakubowski.player.Point;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectPointsForTieException;
import com.przemyslawjakubowski.gameConfiguration.configurationExceptions.IncorrectPointsForWonException;

public class PointsConfiguration {
    private Point pointsForWon;
    private Point pointsForTie;

    public PointsConfiguration() {
        this.pointsForWon = new Point(0);
        this.pointsForTie = new Point(0);
    }

    public void setPointsForWonRound(Point pointsForWonRound) throws IncorrectPointsForWonException {
        if(pointsForWonRound.getValue() > 0){
            this.pointsForWon = pointsForWonRound;
        }
        else{
            throw new IncorrectPointsForWonException(pointsForWonRound.toString());
        }
    }

    public void setPointsForTieRound(Point pointsForTie) throws IncorrectPointsForTieException {
        if(pointsForTie.getValue() < pointsForWon.getValue()){
            this.pointsForTie = pointsForTie;
        }
        else{
            throw new IncorrectPointsForTieException(pointsForTie, pointsForWon);
        }
    }
}
