import com.przemyslawjakubowski.mainStates.RoundFinishedState;
import com.przemyslawjakubowski.mainStates.GameOngoingState;
import com.przemyslawjakubowski.mainStates.GameState;
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
