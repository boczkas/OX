import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.board.BoardRowsConfigurationState;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.mainStates.InitialState;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialStateTest {

    @Test
    public void goToNextState_InitToGameOngoing(){
        // given
        GameState gameState = new InitialState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), BoardRowsConfigurationState.class);
    }

    @Test
    public void afterPerformActionOfInitState_2_playersArePresent(){
        // given
        String playersNames = "winner\nloser\n";
        System.setIn(new ByteArrayInputStream(playersNames.getBytes()));
      
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Consumer<String> output = System.out::println;

        // when
        XOGame game = new XOGame(userInputProvider, output);

        InitialState initialState = new InitialState();
        initialState.performAction(userInputProvider, output, game);

        // then
        Players players = game.getPlayers();

        Assert.assertTrue(players.getNextPlayer().getName().equals("winner"));
        Assert.assertTrue(players.getNextPlayer().getName().equals("loser"));

        // @After
        System.setIn(System.in);
    }
}
