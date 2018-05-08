package gameConfiguration;

import com.przemyslawjakubowski.XOGame;
import com.przemyslawjakubowski.board.BoardColumnsConfigurationState;
import com.przemyslawjakubowski.board.BoardRowsConfigurationState;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWinConfigurationState;
import com.przemyslawjakubowski.mainStates.GameState;
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

public class BoardRowsConfigurationStateTest {

    @Test
    public void goToNextState_rowsEntryCorrect_returns_BoardColumnsConfigurationState(){
        // given
        String rowsEntry = "3\n";

        System.setIn(new ByteArrayInputStream(rowsEntry.getBytes()));

        UserInputProvider userInputProvider = new UserInputProvider(new Scanner(System.in)::nextLine);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        OutputConsumer outputConsumer = new OutputConsumer(System.out::println);
        LanguageStrings languageStrings = LanguageFileReader.getLanguageStringsFromFile("ENG.lang", outputConsumer);
        outputConsumer = new OutputConsumer(System.out::println, languageStrings);

        // when
        XOGame game = new XOGame(userInputProvider, outputConsumer);
        GameState boardRowsConfigurationState = new BoardRowsConfigurationState();

        boardRowsConfigurationState.performAction(userInputProvider, outputConsumer, game);

        // then
        Assert.assertTrue(boardRowsConfigurationState.goToNextState().getClass().equals(BoardColumnsConfigurationState.class));
    }
}
