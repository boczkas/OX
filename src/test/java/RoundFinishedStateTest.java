import com.przemyslawjakubowski.mainStates.RoundFinishedState;
import com.przemyslawjakubowski.mainStates.GameState;
import com.przemyslawjakubowski.mainStates.InitialState;

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
