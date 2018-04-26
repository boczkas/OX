import com.przemyslawjakubowski.states.GameFinishedState;
import com.przemyslawjakubowski.states.GameState;
import com.przemyslawjakubowski.states.InitialState;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GameFinishedStateTest {
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
