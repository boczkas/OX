import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.InitialState;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialStateTest {

    @Test
    public void goToNextState_InitToGameOngoing(){
        // given
        GameState gameState = new com.przemyslawjakubowski.InitialState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), GameOngoingState.class);
    }

    @Test
    public void afterPerformActionOfInitState_2_playersArePresent(){
        // given
        String playersNames = "winner\nloser\n";
        System.setIn(new ByteArrayInputStream(playersNames.getBytes()));
      
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        Consumer<String> output = System.out::println;

        // when
        XOGame game = new XOGame(userInputProvider, output);

        InitialState initialState = new InitialState();
        initialState.performAction(userInputProvider, output, game);

        // then
        Players players = game.getPlayers();

        Assert.assertTrue(players.getPlayers().get(0).getName().equals("winner"));
        Assert.assertTrue(players.getPlayers().get(1).getName().equals("loser"));

        // @After
        System.setIn(System.in);
    }
}
