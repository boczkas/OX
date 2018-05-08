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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SymbolsToWinConfigurationStateTest {
    String symbolsEntry;
    OutputConsumer outputConsumer;
    UserInputProvider userInputProvider;
    ByteArrayOutputStream outContent;
    LanguageStrings languageStrings;
    XOGame game;

    @BeforeMethod
    public void setUp(){
        symbolsEntry = "3\n";
        outputConsumer = new OutputConsumer(System.out::println);
        System.setIn(new ByteArrayInputStream(symbolsEntry.getBytes()));
        userInputProvider = new UserInputProvider(new Scanner(System.in)::nextLine);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outputConsumer = new OutputConsumer(System.out::println);
        languageStrings = LanguageFileReader.getLanguageStringsFromFile("ENG.lang", outputConsumer);
        outputConsumer = new OutputConsumer(System.out::println, languageStrings);
        game = new XOGame(userInputProvider, outputConsumer);
    }

    @Test
    public void goToNextState_symbolsEntryCorrect_returns_StartingPlayerConfigurationState(){
        // given


        // when
        GameState symbolsToWinConfigurationState = new SymbolsToWinConfigurationState();
        game.getBoardStatus().setBoardColumns(3, outputConsumer);
        game.getBoardStatus().setBoardRows(3, outputConsumer);
        symbolsToWinConfigurationState.performAction(userInputProvider, outputConsumer, game);

        // then
        Assert.assertTrue(symbolsToWinConfigurationState.goToNextState().getClass().equals(StartingPlayerConfigurationState.class));
    }

    @Test
    public void goToNextState_symbolsEntryIncorrect_returns_SymbolsToWinConfigurationState(){

        // when
        GameState symbolsToWinConfigurationState = new SymbolsToWinConfigurationState();
        game.getBoardStatus().setBoardColumns(2, outputConsumer);
        game.getBoardStatus().setBoardRows(2, outputConsumer);
        symbolsToWinConfigurationState.performAction(userInputProvider, outputConsumer, game);

        // then
        Assert.assertTrue(symbolsToWinConfigurationState.goToNextState().getClass().equals(SymbolsToWinConfigurationState.class));
    }
}
