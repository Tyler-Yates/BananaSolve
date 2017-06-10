package tyates.bananasolve.data;

import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Chars;
import org.junit.Before;
import org.junit.Test;
import tyates.bananasolve.dictionary.Dictionaries;
import tyates.bananasolve.util.Direction;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class NodeBoardTest {
    private static final Set<String> VALID_WORDS = ImmutableSet.of("foot", "box", "toe", "ox");

    private NodeBoard nodeBoard;

    @Before
    public void setup() {
        nodeBoard = new NodeBoard(Dictionaries.from(VALID_WORDS));
    }

    @Test
    public void testAddWord() {
        assertWordAdded("foot", "foot", 0, 0, Direction.DOWN);
        assertWordAdded("oot", "foot", 0, 0, Direction.RIGHT);
        System.out.println(nodeBoard.getWords());
    }

    private void assertWordAdded(final String expectedAddedCharacters, final String word, final int r, final int c, final Direction direction) {
        assertEquals(Chars.asList(expectedAddedCharacters.toCharArray()), nodeBoard.addWord(word, r, c, direction));
    }
}