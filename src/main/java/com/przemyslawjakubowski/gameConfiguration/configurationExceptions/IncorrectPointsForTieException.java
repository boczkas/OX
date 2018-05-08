package com.przemyslawjakubowski.gameConfiguration.configurationExceptions;

import com.przemyslawjakubowski.output.OutputConsumer;
import com.przemyslawjakubowski.output.OutputOption;
import com.przemyslawjakubowski.output.ReplacePattern;
import com.przemyslawjakubowski.player.Point;

public class IncorrectPointsForTieException extends Throwable {

    Point pointsForTie;
    Point pointsFroWin;

    public IncorrectPointsForTieException(Point pointsForTie, Point pointsForWin) {
        this.pointsForTie = pointsForTie;
        this.pointsFroWin = pointsForWin;
    }

    public void printExceptionMessage(OutputConsumer outputConsumer){
        outputConsumer.accept("=============================================");
        outputConsumer.accept(OutputOption.EXCEPTION_INCORRECT_POINTS_FOR_TIE, new ReplacePattern("%pointsForTie%", pointsForTie.toString()),
                                                                               new ReplacePattern("%pointsForWin%", pointsFroWin.toString()));
        outputConsumer.accept("=============================================\n");
    }
}
