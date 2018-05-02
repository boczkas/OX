import com.przemyslawjakubowski.states.RoundFinishedState;
import com.przemyslawjakubowski.states.GameState;
import com.przemyslawjakubowski.states.InitialState;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RoundFinishedStateTest {
    @Test
    public void goToNextState_GameFinishedStateToInitialState(){
        // given
        GameState gameState = new RoundFinishedState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), InitialState.class);
    }

}
