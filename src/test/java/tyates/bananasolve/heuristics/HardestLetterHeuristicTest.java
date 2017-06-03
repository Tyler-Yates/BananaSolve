package tyates.bananasolve.heuristics;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HardestLetterHeuristicTest {
    private OrderingHeuristic heuristic;

    @Before
    public void setUp() throws Exception {
        heuristic = new HardestLetterHeuristic();
    }

    @Test
    public void orderWords_1() throws Exception {
        final ImmutableSet<String> words = ImmutableSet.of("zz", "zxjk", "bob");
        final ImmutableSet<String> expected = ImmutableSet.of("zxjk", "zz", "bob");
        assertEquals(expected, heuristic.orderWords(words));
    }

    @Test
    public void orderWords_tie() throws Exception {
        // When there is a tie for number of hardest characters, length of word becomes the tie breaker
        final ImmutableSet<String> words = ImmutableSet.of("zooo", "zoo", "zo", "bob");
        final ImmutableSet<String> expected = ImmutableSet.of("zo", "bob", "zooo", "zoo");
        assertEquals(expected, heuristic.orderWords(words));
    }
}