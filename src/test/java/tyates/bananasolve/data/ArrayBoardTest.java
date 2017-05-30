package tyates.bananasolve.data;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import tyates.bananasolve.dictionary.Dictionaries;
import tyates.bananasolve.util.Direction;

import java.util.Set;

import static org.junit.Assert.*;

public class ArrayBoardTest {
    private static final Set<String> VALID_WORDS = ImmutableSet.of("foot", "box", "toe", "ox");

    private Board arrayBoard;

    @Before
    public void setUp() throws Exception {
        arrayBoard = new ArrayBoard(Dictionaries.from(VALID_WORDS));
    }

    @Test
    public void addWord_InvalidDown_1() throws Exception {
        assertTrue(arrayBoard.addWord("foot", 0, 1, Direction.DOWN));
        assertTrue(arrayBoard.addWord("toe", 3, 1, Direction.RIGHT));
        assertFalse(arrayBoard.addWord("box", 2, 0, Direction.RIGHT));
        System.out.println(arrayBoard.toString());
    }

    @Test
    public void addWord_InvalidDown_2() throws Exception {
        assertTrue(arrayBoard.addWord("foot", 0, 0, Direction.DOWN));
        assertTrue(arrayBoard.addWord("toe", 3, 0, Direction.RIGHT));
        assertFalse(arrayBoard.addWord("ox", 2, 0, Direction.RIGHT));
        System.out.println(arrayBoard.toString());
    }
}