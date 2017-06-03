package tyates.bananasolve.data;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import tyates.bananasolve.dictionary.Dictionaries;
import tyates.bananasolve.util.Direction;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ArrayBoardTest {
    private static final Set<String> VALID_WORDS = ImmutableSet.of("foot", "box", "toe", "ox");

    private Board arrayBoard;

    @Before
    public void setUp() throws Exception {
        arrayBoard = new ArrayBoard(Dictionaries.from(VALID_WORDS));
    }

    @Test
    public void addWord_InvalidDown_1() throws Exception {
        assertCharsAdded("foot", "foot", 0, 1, Direction.DOWN);
        assertCharsAdded("oe", "toe", 3, 1, Direction.RIGHT);

        // This should fail because "xo" is not a word
        assertNull(arrayBoard.addWord("box", 2, 0, Direction.RIGHT));

        System.out.println(arrayBoard.toString());
    }

    @Test
    public void addWord_InvalidDown_2() throws Exception {
        assertCharsAdded("foot", "foot", 0, 0, Direction.DOWN);
        assertCharsAdded("oe", "toe", 3, 0, Direction.RIGHT);

        // This should fail because "xo" is not a word
        assertNull(arrayBoard.addWord("ox", 2, 0, Direction.RIGHT));

        System.out.println(arrayBoard.toString());
    }

    @Test
    public void addWord_InvalidMass_1() throws Exception {
        assertCharsAdded("foot", "foot", 0, 0, Direction.DOWN);

        // This should fail because it does not connect with the existing word
        assertNull(arrayBoard.addWord("foot", 0, 2, Direction.RIGHT));

        // This should pass because it connects with the existing word
        assertCharsAdded("oot", "foot", 0, 0, Direction.RIGHT);

        System.out.println(arrayBoard.toString());
    }

    private void assertCharsAdded(final String expectedCharsAdded, final String word, final int r, final int c,
                                  final Direction direction) {
        assertEquals(Lists.charactersOf(expectedCharsAdded), arrayBoard.addWord(word, r, c, direction));
    }
}