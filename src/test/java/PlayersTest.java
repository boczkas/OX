import com.przemyslawjakubowski.mainStates.InitialState;
import com.przemyslawjakubowski.player.Player;
import com.przemyslawjakubowski.player.Players;
import com.przemyslawjakubowski.player.Point;
import com.przemyslawjakubowski.player.Symbol;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlayersTest {

    @Test
    public void getStartingPlayer_afterSetStartingPlayer_returnsCorrectPlayer(){
        // given
        Player przemek = new Player("Przemek", Symbol.X, new Point(0));
        Player bartek = new Player("Bartek", Symbol.O, new Point(0));
        Players players = new Players();

        // when
        players.addPlayer(przemek, new InitialState());
        players.addPlayer(bartek, new InitialState());

        players.setStartingPlayer(przemek);
        Player current = players.getStartingPlayer();

        // then
        Assert.assertEquals(current.getSymbol(), przemek.getSymbol());
    }

    @Test
    public void getStartingPlayer_startingWithX_returnsXOX(){
        // given
        Player przemek = new Player("Przemek", Symbol.X, new Point(0));
        Player bartek = new Player("Bartek", Symbol.O, new Point(0));
        Players players = new Players();
        players.addPlayer(przemek, new InitialState());
        players.addPlayer(bartek, new InitialState());

        //when
        players.setStartingPlayer(przemek);
        players.getStartingPlayer();
        players.getStartingPlayer();
        Player startingPlayer = players.getStartingPlayer();

        //then
        Assert.assertEquals(startingPlayer.getSymbol(), Symbol.X);
    }

    @Test
    public void getStartingPlayer_startingWithO_returnsOXO(){
        // given
        Player przemek = new Player("Przemek", Symbol.X, new Point(0));
        Player bartek = new Player("Bartek", Symbol.O, new Point(0));
        Players players = new Players();
        players.addPlayer(przemek, new InitialState());
        players.addPlayer(bartek, new InitialState());

        //when
        players.setStartingPlayer(bartek);
        players.getStartingPlayer();
        players.getStartingPlayer();
        Player startingPlayer = players.getStartingPlayer();

        //then
        Assert.assertEquals(startingPlayer.getSymbol(), Symbol.O);
    }

    @Test
    public void getStarting_getNext_x2_startingWithX_returnsXOX(){
        // given
        Player przemek = new Player("Przemek", Symbol.X, new Point(0));
        Player bartek = new Player("Bartek", Symbol.O, new Point(0));
        Players players = new Players();
        players.addPlayer(przemek, new InitialState());
        players.addPlayer(bartek, new InitialState());

        // when
        players.setStartingPlayer(przemek);
        players.getStartingPlayer();
        players.getNextPlayer();
        Player current = players.getNextPlayer();

        // then
        Assert.assertEquals(current.getSymbol(), Symbol.X);
    }
}
