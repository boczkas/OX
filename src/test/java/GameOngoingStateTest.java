import com.przemyslawjakubowski.*;
import com.przemyslawjakubowski.GameFinishedState;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameOngoingStateTest {
    @Test
    public void goToNextState_GameOngoingStateToGameFinishedState(){
        // given
        GameState gameState = new GameOngoingState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), GameFinishedState.class);
    }

    @Test
    public void inGameOngoingStateCorrectUsersInputAreAccepted_board3x3(){
//        // given
//        String correctCoordinates = "11\n22\n23\n";
//        System.setIn(new ByteArrayInputStream(correctCoordinates.getBytes()));
//        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
//        Consumer<String> output = System.out::println;
//
//        // when
//        XOGame game = new XOGame(userInputProvider, output);
//
//        GameOngoingState gameOngoingState = new GameOngoingState();
//        gameOngoingState.performAction(userInputProvider, output, game);
    }

}
