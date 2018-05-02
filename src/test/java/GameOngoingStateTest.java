import com.przemyslawjakubowski.states.RoundFinishedState;
import com.przemyslawjakubowski.states.GameOngoingState;
import com.przemyslawjakubowski.states.GameState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameOngoingStateTest {
    @Test
    public void goToNextState_GameOngoingStateToGameFinishedState(){
        // given
        GameState gameState = new GameOngoingState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), RoundFinishedState.class);
    }
}
