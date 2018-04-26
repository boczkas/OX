import com.przemyslawjakubowski.Board;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BoardTest {

    @Test
    public void inGameOngoingStateIncorrectUserInputsAreNotAccepted_board3x3(){
        // given
        String inputSequence = "1 2\n";
        System.setIn(new ByteArrayInputStream(inputSequence.getBytes()));
        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Consumer<String> output = System.out::println;

        // when
        Board board = new Board(3,3);
        board.handleMoves(userInputProvider, output);

        // then
        String[] outArray = outContent.toString().split("\n");
        Assert.assertEquals("Ładnie powiedziane", outArray[outArray.length - 1]);
//        System.setOut(System.out);
//        System.out.println(outContent.toString());
    }
}

/*


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

        */