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
}
