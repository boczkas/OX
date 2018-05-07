import com.przemyslawjakubowski.board.BoardConfiguration;
import com.przemyslawjakubowski.board.BoardStatus;
import com.przemyslawjakubowski.board.Coordinate;
import com.przemyslawjakubowski.Judge;
import com.przemyslawjakubowski.gameConfiguration.SymbolsToWin;
import com.przemyslawjakubowski.player.Symbol;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Consumer;

public class JudgeTest {
    @Test
    public void isWinner_XinVerticalSequenceOf3_On_3x3_board_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge = new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(1,0));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,0));
        judge.checkWin(new Coordinate(2,0));

        // then
        Assert.assertTrue(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinVerticalSequenceOf2_On_3x3_board_returnsFalse(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge = new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(1,0));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,0));
        judge.checkWin(new Coordinate(2,0));

        // then
        Assert.assertFalse(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinHorizontalSequenceOf3_On_3x3_board_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge =  new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,1));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,2));
        judge.checkWin(new Coordinate(0,2));

        // then
        Assert.assertTrue(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinHorizontalSequenceOf2_On_3x3_board_returnsFalse(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge = new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(0,1));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,2));
        judge.checkWin(new Coordinate(0,2));

        // then
        Assert.assertFalse(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinLeftRightDiagonalSequenceOf3_On_3x3_board_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge =  new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(1,1));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,2));
        judge.checkWin(new Coordinate(2,2));

        // then
        Assert.assertTrue(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinLeftRightDiagonalSequenceOf3_On_3x3_board_returnsFalse(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge =  new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,0));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(1,1));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,2));
        judge.checkWin(new Coordinate(2,2));

        // then
        Assert.assertFalse(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinRightLeftDiagonalSequenceOf3_On_3x3_board_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge =  new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,2));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(1,1));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,0));
        judge.checkWin(new Coordinate(2,0));

        // then
        Assert.assertTrue(judge.isWinnerPresent());
    }

    @Test
    public void isWinner_XinRightLeftDiagonalSequenceOf2_On_3x3_board_returnsFalse(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge = new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,2));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(1,1));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,0));
        judge.checkWin(new Coordinate(2,0));

        // then
        Assert.assertFalse(judge.isWinnerPresent());
    }

    @Test
    public void isTie_boardWithTie_returnsTrue(){
        // given
        BoardStatus boardStatus = new BoardStatus(new BoardConfiguration());
        Consumer<String> output = System.out::println;
        boardStatus.setBoardColumns(3, output);
        boardStatus.setBoardRows(3, output);
        Judge judge = new Judge(boardStatus, new SymbolsToWin(3, boardStatus));

        // when
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(0,0));
        judge.checkWin(new Coordinate(0,0));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(0,1));
        judge.checkWin(new Coordinate(0,1));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(0,2));
        judge.checkWin(new Coordinate(0,2));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(1,0));
        judge.checkWin(new Coordinate(1,0));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(1,1));
        judge.checkWin(new Coordinate(1,1));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(1,2));
        judge.checkWin(new Coordinate(1,2));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,0));
        judge.checkWin(new Coordinate(2,0));
        boardStatus.addSymbolAtCoordinate(Symbol.O, new Coordinate(2,1));
        judge.checkWin(new Coordinate(2,1));
        boardStatus.addSymbolAtCoordinate(Symbol.X, new Coordinate(2,2));
        judge.checkWin(new Coordinate(2,2));


        // then
        Assert.assertTrue(judge.isTie());
    }
}
