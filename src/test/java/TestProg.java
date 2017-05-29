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
        System.out.println(board.addWord("question", 0, 0, Direction.DOWN));
        System.out.println(board.addWord("quote", 0, 0, Direction.RIGHT));
        System.out.println(board.toString());
        System.out.println(board.addWord("questions", 0, 0, Direction.DOWN));
        System.out.println(board.toString());
        System.out.println(board.addWord("questions", 1, 0, Direction.DOWN));
        System.out.println(board.toString());
        System.out.println(board.getTiles());
        System.out.println(board.getWords());
    }
}
