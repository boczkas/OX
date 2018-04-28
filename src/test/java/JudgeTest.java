import com.przemyslawjakubowski.BoardStatus;
import com.przemyslawjakubowski.Coordinate;
import com.przemyslawjakubowski.Judge;
import com.przemyslawjakubowski.player.Symbol;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JudgeTest {
    @Test
    public void isWinner_XinVerticalSequenceOf3On_3x3_board_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(3, 3);
        Judge judge = new Judge(boardStatus, 3);

        // when
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(0,1));
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(0,2));
        judge.checkWinner(new Coordinate(0,2));

        // then
        Assert.assertTrue(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinHorizontalSequenceOf3On_3x3_board_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(3,3);
        Judge judge = new Judge(boardStatus, 3);

        // when
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(1,0));
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(2,0));
        judge.checkWinner(new Coordinate(2,0));

        // then
        Assert.assertTrue(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinLeftDiagonalSequenceOf3On_3x3_board_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(3,3);
        Judge judge = new Judge(boardStatus, 3);

        // when
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(1,1));
        boardStatus.addSymbolAtPosition(Symbol.X, new Coordinate(2,2));
        judge.checkWinner(new Coordinate(2,2));

        // then
        Assert.assertTrue(judge.isWinnerPresent());
    }
}
