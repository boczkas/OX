package com.przemyslawjakubowski.output;

import java.util.HashMap;
import java.util.Map;

public class LanguageStrings {
    private Map<OutputOption, String> languageStrings;

    LanguageStrings() {
        languageStrings = new HashMap<>();
        initializeDefaultsLanguageStrings();
    }

    private void initializeDefaultsLanguageStrings() {

        languageStrings.put(OutputOption.STARTING, "Starting");
        languageStrings.put(OutputOption.FIRST_PLAYER_NAME_QUESTION, "Specify first player name");
        languageStrings.put(OutputOption.SECOND_PLAYER_NAME_QUESTION, "Specify second player name");
        languageStrings.put(OutputOption.STARTING_PLAYER_QUESTION, "Which player should start? X or O");
        languageStrings.put(OutputOption.WIDTH_QUESTION, "How wide should board be?");
        languageStrings.put(OutputOption.LENGTH_QUESTION, "How long should board be?");
        languageStrings.put(OutputOption.SYMBOLS_AMOUNT_QUESTION, "How many symbols give won?");
        languageStrings.put(OutputOption.INCORRECT_VALUE, "Incorrect value");
        languageStrings.put(OutputOption.CURRENTLY_PLAYING, "Currently playing: %playerName% (%playerSymbol%)");
        languageStrings.put(OutputOption.PLAYER_SCORE, "Player: %playerName% score: %playerScore%");
        languageStrings.put(OutputOption.CONGRATULATION, "Congratulation");
        languageStrings.put(OutputOption.WON, "Win: %winnerSymbol%. %winnerSymbol%: %winnerScore% %loserSymbol%: %loserScore%");
        languageStrings.put(OutputOption.ROUND_WON_BY_PLAYER, "Round won by player: %playerName%!");
        languageStrings.put(OutputOption.TIE, "Tie!");
        languageStrings.put(OutputOption.EXCEPTION_BOARD_INDEX_OUT_OF_BOUND, "Index out of board bound!");
        languageStrings.put(OutputOption.EXCEPTION_FIELD_NOT_EMPTY, "Field %field% is not empty. Specify empty field");
        languageStrings.put(OutputOption.EXCEPTION_INCORRECT_SYMBOL, "Incorrect symbol\n You should specify one of X/O.\n Entry : %symbol%Incorrect symbol\n You should specify one of X/O.\n Entry : %symbol%");
        languageStrings.put(OutputOption.EXCEPTION_INCORRECT_COORDINATE, "%text% value is incorrect!");
        languageStrings.put(OutputOption.EXCEPTION_INCORRECT_AMOUNT_OF_SYMBOLS_TO_WIN, "It is not possible to won on board %rows%x%columns% using %symbolsToWin% symbols");
        languageStrings.put(OutputOption.EXCEPTION_INCORRECT_POINTS_FOR_TIE, "Value %pointsForTie% can not be set as \n value of points got for tie it has to be \nbigger than amount of points for win : %pointsForWin%");
        languageStrings.put(OutputOption.EXCEPTION_INCORRECT_POINTS_FOR_WON, "%pointsForWin% can not be set as \n value of points got for win \nit has to be > 0\n!");
        languageStrings.put(OutputOption.EXCEPTION_BOARD_DIMENSION, "%pointsForWin% can not be set as \n value of points got for win \nit has to be > 0\n!");
    }

    void addLanguageString(OutputOption key, String value){
        languageStrings.replace(key, value);
    }

    String getLanguageString(OutputOption key){
        return languageStrings.get(key);
    }
}
