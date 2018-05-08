package com.przemyslawjakubowski.output;

import java.util.HashMap;
import java.util.Map;

public class LanguageStrings {
    private Map<OutputOption, String> languageStrings;
    private Map<OutputOption, String> defaultLanguageStrings;

    LanguageStrings() {
        languageStrings = new HashMap<>();
        defaultLanguageStrings = new HashMap<>();
        initializeDefaultsLanguageStrings();
    }

    private void initializeDefaultsLanguageStrings() {

        defaultLanguageStrings.put(OutputOption.STARTING, "Starting");
        defaultLanguageStrings.put(OutputOption.FIRST_PLAYER_NAME_QUESTION, "Specify first player name");
        defaultLanguageStrings.put(OutputOption.SECOND_PLAYER_NAME_QUESTION, "Specify second player name");
        defaultLanguageStrings.put(OutputOption.STARTING_PLAYER_QUESTION, "Which player should start? X or O");
        defaultLanguageStrings.put(OutputOption.WIDTH_QUESTION, "How wide should board be?");
        defaultLanguageStrings.put(OutputOption.LENGTH_QUESTION, "How long should board be?");
        defaultLanguageStrings.put(OutputOption.SYMBOLS_AMOUNT_QUESTION, "How many symbols give won?");
        defaultLanguageStrings.put(OutputOption.INCORRECT_VALUE, "Incorrect value");
        defaultLanguageStrings.put(OutputOption.CURRENTLY_PLAYING, "Currently playing: %playerName% (%playerSymbol%)");
        defaultLanguageStrings.put(OutputOption.PLAYER_SCORE, "Player: %playerName% score: %playerScore%");
        defaultLanguageStrings.put(OutputOption.CONGRATULATION, "Congratulation");
        defaultLanguageStrings.put(OutputOption.WON, "Win: %winnerSymbol%. %winnerSymbol%: %winnerScore% %loserSymbol%: %loserScore%");
        defaultLanguageStrings.put(OutputOption.ROUND_WON_BY_PLAYER, "Round won by player: %playerName%!");
        defaultLanguageStrings.put(OutputOption.TIE, "Tie!");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_BOARD_INDEX_OUT_OF_BOUND, "Index out of board bound!");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_FIELD_NOT_EMPTY, "Field %field% is not empty. Specify empty field");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_INCORRECT_SYMBOL, "Incorrect symbol\n You should specify one of X/O.\n Entry : %symbol%Incorrect symbol\n You should specify one of X/O.\n Entry : %symbol%");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_INCORRECT_COORDINATE, "%text% value is incorrect!");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_INCORRECT_AMOUNT_OF_SYMBOLS_TO_WIN, "It is not possible to won on board %rows%x%columns% using %symbolsToWin% symbols");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_INCORRECT_POINTS_FOR_TIE, "Value %pointsForTie% can not be set as \n value of points got for tie it has to be \nbigger than amount of points for win : %pointsForWin%");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_INCORRECT_POINTS_FOR_WON, "%pointsForWin% can not be set as \n value of points got for win \nit has to be > 0\n!");
        defaultLanguageStrings.put(OutputOption.EXCEPTION_BOARD_DIMENSION, "%pointsForWin% can not be set as \n value of points got for win \nit has to be > 0\n!");
    }

    void addLanguageString(OutputOption key, String value){
        languageStrings.put(key, value);
    }

    String getLanguageString(OutputOption key){
        if(languageStrings.containsKey(key))
            return languageStrings.get(key);
        return defaultLanguageStrings.get(key);
    }
}
