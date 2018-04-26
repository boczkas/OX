import com.przemyslawjakubowski.GameState;
import com.przemyslawjakubowski.InitialState;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameFinishedState {
    @Test
    public void goToNextState_GameFinishedStateToInitialState(){
        // given
        GameState gameState = new com.przemyslawjakubowski.GameFinishedState();

        // when
        gameState = gameState.goToNextState();

        // then
        Assert.assertEquals(gameState.getClass(), InitialState.class);
    }

}
