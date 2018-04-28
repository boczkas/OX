public class BoardTest {

//    @Test
//    public void inGameOngoingStateIncorrectUserInputsAreNotAccepted_board3x3(){
//        // given
//        String inputSequence = "1 2\n";
//        System.setIn(new ByteArrayInputStream(inputSequence.getBytes()));
//        Supplier<String> userInputProvider = new Scanner(System.in)::nextLine;
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        Consumer<String> output = System.out::println;
//
//        // when
//        MovesHandler board = new MovesHandler(new BoardStatus(3,3));
//        board.handleMoves(userInputProvider, output, new Player("Przemek", Symbol.X));
//
//        // then
//        String[] outArray = outContent.toString().split("\n");
//        Assert.assertEquals("Ładnie powiedziane", outArray[outArray.length - 1]);
//    }
}