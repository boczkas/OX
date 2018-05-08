package gameConfiguration;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.BoardColumnsConfigurationState;
import com.przemyslawjakubowski.board.BoardRowsConfigurationState;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWinConfigurationState;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.player.StartingPlayerConfigurationState;
import com.przemyslawjakubowski.textOutput.LanguageFileReader;
import com.przemyslawjakubowski.textOutput.LanguageStrings;
import com.przemyslawjakubowski.textOutput.OutputConsumer;
import com.przemyslawjakubowski.userInput.UserInputProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SymbolsToWinConfigurationStateTest {

    @Test
    public void goToNextState_symbolsEntryCorrect_returns_StartingPlayerConfigurationState(){
        // given
        String symbolsEntry = "3\n";

        System.setIn(new ByteArrayInputStream(symbolsEntry.getBytes()));

        UserInputProvider userInputProvider = new UserInputProvider(new Scanner(System.in)::nextLine);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        OutputConsumer outputConsumer = new OutputConsumer(System.out::println);
        LanguageStrings languageStrings = LanguageFileReader.getLanguageStringsFromFile("ENG.lang", outputConsumer);
        outputConsumer = new OutputConsumer(System.out::println, languageStrings);

        // when
        XOGame game = new XOGame(userInputProvider, outputConsumer);
        GameState symbolsToWinConfigurationState = new SymbolsToWinConfigurationState();
        game.getBoardStatus().setBoardColumns(3, outputConsumer);
        game.getBoardStatus().setBoardRows(3, outputConsumer);
        symbolsToWinConfigurationState.performAction(userInputProvider, outputConsumer, game);

        // then
        Assert.assertTrue(symbolsToWinConfigurationState.goToNextState().getClass().equals(StartingPlayerConfigurationState.class));
    }

    @Test
    public void goToNextState_symbolsEntryIncorrect_returns_SymbolsToWinConfigurationState(){
        String symbolsEntry = "3\n";

        System.setIn(new ByteArrayInputStream(symbolsEntry.getBytes()));

        UserInputProvider userInputProvider = new UserInputProvider(new Scanner(System.in)::nextLine);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        OutputConsumer outputConsumer = new OutputConsumer(System.out::println);
        LanguageStrings languageStrings = LanguageFileReader.getLanguageStringsFromFile("ENG.lang", outputConsumer);
        outputConsumer = new OutputConsumer(System.out::println, languageStrings);

        // when
        XOGame game = new XOGame(userInputProvider, outputConsumer);
        GameState symbolsToWinConfigurationState = new SymbolsToWinConfigurationState();
        game.getBoardStatus().setBoardColumns(2, outputConsumer);
        game.getBoardStatus().setBoardRows(2, outputConsumer);
        symbolsToWinConfigurationState.performAction(userInputProvider, outputConsumer, game);

        // then
        Assert.assertTrue(symbolsToWinConfigurationState.goToNextState().getClass().equals(SymbolsToWinConfigurationState.class));
    }
}
