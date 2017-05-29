import org.junit.Test;
import tyates.bananasolve.data.ArrayBoard;
import tyates.bananasolve.dictionary.Dictionaries;
import tyates.bananasolve.util.Direction;

import java.io.FileNotFoundException;

public class TestProg {
    @Test
    public void testSimple() throws FileNotFoundException {
        final ArrayBoard board = new ArrayBoard(Dictionaries.compoundDictionary(Dictionaries.oneThousandEnglishWordsDictionary(),
                Dictionaries.fiveThousandEnglishWordsDictionary()));
        System.out.println(board.addWord("question", 10, 10, Direction.DOWN));
        System.out.println(board.addWord("quote", 10, 10, Direction.RIGHT));
        System.out.println(board.toString());
        System.out.println(board.addWord("questions", 10, 10, Direction.DOWN));
        System.out.println(board.toString());
        System.out.println(board.addWord("questions", 11, 10, Direction.DOWN));
        System.out.println(board.addWord("boss", 13, 10, Direction.LEFT));
        System.out.println(board.addWord("battery", 13, 13, Direction.UP));
        System.out.println(board.toString());
        System.out.println(board.getTiles());
        System.out.println(board.getWords());
        System.out.println(board.addWord("say", 13, 10, Direction.RIGHT));
        System.out.println(board.toString());
        System.out.println(board.getTiles());
        System.out.println(board.getWords());
    }
}
