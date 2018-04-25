import com.przemyslawjakubowski.GameFinishedState;
import com.przemyslawjakubowski.GameOngoingState;
import com.przemyslawjakubowski.GameState;
import com.przemyslawjakubowski.InitialState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameStateTest {
    @Test
    public void goToNextState_InitToGameOngoing(){
        // given
        GameState gameState = new InitialState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), GameOngoingState.class);
    }

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
    public void goToNextState_GameFinishedStateToInitialState(){
        // given
        GameState gameState = new GameFinishedState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), InitialState.class);
    }

}
