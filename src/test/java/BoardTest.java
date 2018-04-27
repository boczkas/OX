import com.przemyslawjakubowski.Board;
import com.przemyslawjakubowski.BoardStatus;
import com.przemyslawjakubowski.Player;
import com.przemyslawjakubowski.Symbol;
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
        Board board = new Board(new BoardStatus(3,3));
        board.handleMoves(userInputProvider, output, new Player("Przemek", Symbol.X));

        // then
        String[] outArray = outContent.toString().split("\n");
        Assert.assertEquals("Ładnie powiedziane", outArray[outArray.length - 1]);
    }
}