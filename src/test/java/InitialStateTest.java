import com.przemyslawjakubowski.InitialState;
import com.przemyslawjakubowski.Player;
import com.przemyslawjakubowski.XOGame;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialStateTest {

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
        List<Player> players = game.getPlayers();

        Assert.assertTrue(players.get(0).getName().equals("winner"));
        Assert.assertTrue(players.get(1).getName().equals("loser"));

        // @After
        System.setIn(System.in);

    }
}
